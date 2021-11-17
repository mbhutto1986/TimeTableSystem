package com.mbhutto.entity;

import com.mbhutto.database.TeacherDataOperations;

import java.sql.*;
import java.sql.ResultSet;

public class Teacher
{
	private long     teacherId				;
	private Department department       	;
	private String   teacherName  	  	    ;
	private String   teacherNicNumber  		;
	private String   teacherHomePhone		;
	private String   teacherOfficePhone		;
	private String   teacherMobilePhone		;
	private String   teacherEmail			;
	private String   teacherQualifications	;
	private String   teacherDesignation		;
	private byte     teacherGrade           ;
	private ResultSet resultSet 			;
	
	

	// Constructor with name only, called at the time of delete record.
	public Teacher(String teacherName)
	{
		this.teacherName = teacherName;
	}

	// Constructor with all fields/ Properties
	public Teacher(long teacherId, Department department, String teacherName, String teacherNicNumber, String teacherHomePhone, String teacherOfficePhone, String teacherMobilePhone, String teacherEmail, String teacherQualifications, String teacherDesignation, byte teacherGrade)
	{
		this.teacherId = teacherId;
		this.department = department;
		this.teacherName = teacherName;
		this.teacherNicNumber = teacherNicNumber;
		this.teacherHomePhone = teacherHomePhone;
		this.teacherOfficePhone = teacherOfficePhone;
		this.teacherMobilePhone = teacherMobilePhone;
		this.teacherEmail = teacherEmail;
		this.teacherQualifications = teacherQualifications;
		this.teacherDesignation = teacherDesignation;
		this.teacherGrade = teacherGrade;

	}

	public Teacher(long teacherId, String teacherName, String teacherNicNumber, String teacherHomePhone, String teacherOfficePhone, String teacherMobilePhone, String teacherEmail, String teacherQualifications, String teacherDesignation, byte teacherGrade)
	{
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.teacherNicNumber = teacherNicNumber;
		this.teacherHomePhone = teacherHomePhone;
		this.teacherOfficePhone = teacherOfficePhone;
		this.teacherMobilePhone = teacherMobilePhone;
		this.teacherEmail = teacherEmail;
		this.teacherQualifications = teacherQualifications;
		this.teacherDesignation = teacherDesignation;
		this.teacherGrade = teacherGrade;

	}

	public Teacher() {

	}



	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherNicNumber() {
		return teacherNicNumber;
	}

	public void setTeacherNicNumber(String teacherNicNumber) {
		this.teacherNicNumber = teacherNicNumber;
	}

	public String getTeacherHomePhone() {
		return teacherHomePhone;
	}

	public void setTeacherHomePhone(String teacherHomePhone) {
		this.teacherHomePhone = teacherHomePhone;
	}

	public String getTeacherOfficePhone() {
		return teacherOfficePhone;
	}

	public void setTeacherOfficePhone(String teacherOfficePhone) {
		this.teacherOfficePhone = teacherOfficePhone;
	}

	public String getTeacherMobilePhone() {
		return teacherMobilePhone;
	}

	public void setTeacherMobilePhone(String teacherMobilePhone) {
		this.teacherMobilePhone = teacherMobilePhone;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherQualifications() {
		return teacherQualifications;
	}

	public void setTeacherQualifications(String teacherQualifications) {
		this.teacherQualifications = teacherQualifications;
	}

	public String getTeacherDesignation() {
		return teacherDesignation;
	}

	public void setTeacherDesignation(String teacherDesignation) {
		this.teacherDesignation = teacherDesignation;
	}

	public byte getTeacherGrade() {
		return teacherGrade;
	}

	public void setTeacherGrade(byte teacherGrade) {
		this.teacherGrade = teacherGrade;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	@Override
	public String toString() {
		return
				 teacherId + ". " +teacherName;

	}

	// Add Record Method call the addTeacher method of TeachersDataBase
	public long addRecord() throws SQLException, ClassNotFoundException {
		return (TeacherDataOperations.addTeacher(this.department, this.teacherName, this.teacherNicNumber, this.teacherHomePhone, this.teacherOfficePhone, this.teacherMobilePhone, this.teacherEmail, this.teacherQualifications, this.teacherDesignation, this.teacherGrade));
	}


	// deleteRecord() Method call the deleteTeacher method of TeachersDataBase
    public int deleteRecord() throws SQLException, ClassNotFoundException {
		return (TeacherDataOperations.deleteTeacher(this.teacherId));
	}


	// search Record() Method call the searchTeacher method of TeachersDataBase
	protected Teacher searchRecord() throws SQLException, ClassNotFoundException
	{
		String[] values  = TeacherDataOperations.searchTeacher(this.teacherName);
		return new Teacher( Long.parseLong(values[0]), new Department(Long.parseLong(values[11]), values[12], values[13], Integer.parseInt(values[14]), Integer.parseInt(values[15])), values[2],values[3],values[4],values[5],values[6],values[7],(values[8]),values[9] ,Byte.parseByte(values[10]) );
	}

	// Update Record Method call the updateTeacher method of TeachersDataBase
	public int updateRecord() throws SQLException, ClassNotFoundException {
		return (TeacherDataOperations.updateTeacher(this.teacherId, this.department, this.teacherName, this.teacherNicNumber, this.teacherHomePhone, this.teacherOfficePhone, this.teacherMobilePhone, this.teacherEmail, this.teacherQualifications, this.teacherDesignation, this.teacherGrade));
	}

	public Result getRecords() throws SQLException, ClassNotFoundException {

		return TeacherDataOperations.getTeachers();
	}

}


