/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.21 : Database - time_table_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`time_table_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `time_table_system`;

/*Table structure for table `departments` */

DROP TABLE IF EXISTS `departments`;

CREATE TABLE `departments` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `department_name` varchar(30) NOT NULL,
  `department_short_name` varchar(30) NOT NULL,
  `department_terms` int NOT NULL,
  `department_sections` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

/*Data for the table `departments` */

/*Table structure for table `departments_terms_sections` */

DROP TABLE IF EXISTS `departments_terms_sections`;

CREATE TABLE `departments_terms_sections` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `department_id` bigint unsigned DEFAULT NULL,
  `term_id` bigint unsigned DEFAULT NULL,
  `section_id` bigint unsigned DEFAULT NULL,
  `is_time_table_set` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `departments_terms_sections_department_id` (`department_id`),
  KEY `departments_terms_sections_term_id` (`term_id`),
  KEY `departments_terms_sections_section_id` (`section_id`),
  CONSTRAINT `departments_terms_sections_department_id` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `departments_terms_sections_section_id` FOREIGN KEY (`section_id`) REFERENCES `sections` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `departments_terms_sections_term_id` FOREIGN KEY (`term_id`) REFERENCES `terms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

/*Data for the table `departments_terms_sections` */

/*Table structure for table `sections` */

DROP TABLE IF EXISTS `sections`;

CREATE TABLE `sections` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `section` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32;

/*Data for the table `sections` */

insert  into `sections`(`id`,`section`) values 
(1,'I'),
(2,'II'),
(3,'III'),
(4,'IV'),
(5,'V');

/*Table structure for table `subjects` */

DROP TABLE IF EXISTS `subjects`;

CREATE TABLE `subjects` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `subject_code` varchar(20) NOT NULL,
  `subject_name` varchar(50) NOT NULL,
  `subject_short_name` varchar(30) NOT NULL,
  `subject_theory_marks` int NOT NULL,
  `subject_theory_classes_in_week` int NOT NULL,
  `subject_practical_marks` int NOT NULL,
  `subject_practical_classes_in_week` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

/*Data for the table `subjects` */

/*Table structure for table `subjects_departments_terms_sections` */

DROP TABLE IF EXISTS `subjects_departments_terms_sections`;

CREATE TABLE `subjects_departments_terms_sections` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `subject_id` bigint unsigned NOT NULL,
  `departments_terms_sections_id` bigint unsigned NOT NULL,
  `is_time_table_set` tinyint(1) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  KEY `subject_id` (`subject_id`),
  KEY `departments_terms_sections_id` (`departments_terms_sections_id`),
  CONSTRAINT `subjects_departments_terms_sections_id` FOREIGN KEY (`departments_terms_sections_id`) REFERENCES `departments_terms_sections` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subjects_departments_terms_sections_subject_id` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `subjects_departments_terms_sections` */

/*Table structure for table `teachers` */

DROP TABLE IF EXISTS `teachers`;

CREATE TABLE `teachers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `department_id` bigint unsigned NOT NULL,
  `teacher_name` varchar(50) NOT NULL,
  `teacher_nic_number` varchar(13) NOT NULL,
  `teacher_home_phone` varchar(12) DEFAULT NULL,
  `teacher_office_phone` varchar(12) NOT NULL,
  `teacher_mobile_phone` varchar(12) DEFAULT NULL,
  `teacher_email` varchar(50) NOT NULL,
  `teacher_qualification` varchar(40) CHARACTER SET utf32 COLLATE utf32_general_ci NOT NULL,
  `teacher_designation` varchar(40) NOT NULL,
  `teacher_grade` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_department_id` (`department_id`),
  CONSTRAINT `teacher_department_id` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

/*Data for the table `teachers` */

/*Table structure for table `teachers_time_table` */

DROP TABLE IF EXISTS `teachers_time_table`;

CREATE TABLE `teachers_time_table` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `teacher_id` bigint unsigned NOT NULL,
  `time_table_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `teachers_time_table` */

/*Table structure for table `terms` */

DROP TABLE IF EXISTS `terms`;

CREATE TABLE `terms` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `term` tinyint unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `terms` */

insert  into `terms`(`id`,`term`) values 
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10);

/*Table structure for table `time_table` */

DROP TABLE IF EXISTS `time_table`;

CREATE TABLE `time_table` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `departments_terms_sections_id` bigint unsigned DEFAULT NULL,
  `subjects_departments_terms_sections_id` bigint unsigned DEFAULT NULL,
  `day` tinyint unsigned NOT NULL,
  `class_no` tinyint unsigned NOT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `allocated` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `subjects_departments_terms_sections_id` (`subjects_departments_terms_sections_id`),
  KEY `time_table_ibfk_2` (`departments_terms_sections_id`),
  CONSTRAINT `time_table_ibfk_1` FOREIGN KEY (`subjects_departments_terms_sections_id`) REFERENCES `subjects_departments_terms_sections` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `time_table_ibfk_2` FOREIGN KEY (`departments_terms_sections_id`) REFERENCES `departments_terms_sections` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `time_table` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
