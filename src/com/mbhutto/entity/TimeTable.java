package com.mbhutto.entity;

import com.mbhutto.database.TimeTableDataOperations;

import java.sql.SQLException;

public class TimeTable
{
    private long timeTableId                    ;
    private long departmentTermSectionId        ;
    private long subjectDepartmentTermSectionId ;
    private int timeTableDay                    ;
    private int timeTableClassNo                ;
    private String timeTableClassType           ;
    private byte allocated                      ;
    private Teacher teacherOne                  ;
    private Teacher teacherTwo                  ;
    private Subject subject                     ;
    private byte isTimeTableSetForSubject       ;
    private byte isTimeTableSetForDeptTermSection;
    private SubjectDepartmentTermSection subjectDepartmentTermSection ;


    public TimeTable() {

    }

    public TimeTable(long departmentTermSectionId) {
        this.departmentTermSectionId = departmentTermSectionId;
    }

    public TimeTable(long timeTableId, long departmentTermSectionId, long subjectDepartmentTermSectionId, int timeTableDay, int timeTableClassNo, String timeTableClassType, byte allocated) {
        this.timeTableId = timeTableId;
        this.departmentTermSectionId = departmentTermSectionId;
        this.subjectDepartmentTermSectionId = subjectDepartmentTermSectionId;
        this.timeTableDay = timeTableDay;
        this.timeTableClassNo = timeTableClassNo;
        this.timeTableClassType = timeTableClassType;
        this.allocated = allocated;
    }

    public long getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(long timeTableId) {
        this.timeTableId = timeTableId;
    }

    public long getDepartmentTermSectionId() {
        return departmentTermSectionId;
    }

    public void setDepartmentTermSectionId(long departmentTermSectionId) {
        this.departmentTermSectionId = departmentTermSectionId;
    }

    public long getSubjectDepartmentTermSectionId() {
        return subjectDepartmentTermSectionId;
    }

    public void setSubjectDepartmentTermSectionId(long subjectDepartmentTermSectionId) {
        this.subjectDepartmentTermSectionId = subjectDepartmentTermSectionId;
    }

    public int getTimeTableDay() {
        return timeTableDay;
    }

    public void setTimeTableDay(int timeTableDay) {
        this.timeTableDay = timeTableDay;
    }

    public int getTimeTableClassNo() {
        return timeTableClassNo;
    }

    public void setTimeTableClassNo(int timeTableClassNo) {
        this.timeTableClassNo = timeTableClassNo;
    }

    public String getTimeTableClassType() {
        return timeTableClassType;
    }

    public void setTimeTableClassType(String timeTableClassType) {
        this.timeTableClassType = timeTableClassType;
    }

    public byte getAllocated() {
        return allocated;
    }

    public void setAllocated(byte allocated) {
        this.allocated = allocated;
    }

    public Teacher getTeacherOne() {
        return teacherOne;
    }

    public void setTeacherOne(Teacher teacherOne) {
        this.teacherOne = teacherOne;
    }

    public Teacher getTeacherTwo() {
        return teacherTwo;
    }

    public void setTeacherTwo(Teacher teacherTwo) {
        this.teacherTwo = teacherTwo;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SubjectDepartmentTermSection getSubjectDepartmentTermSection() {
        return subjectDepartmentTermSection;
    }

    public void setSubjectDepartmentTermSection(SubjectDepartmentTermSection subjectDepartmentTermSection) {
        this.subjectDepartmentTermSection = subjectDepartmentTermSection;
    }

    public byte getIsTimeTableSetForSubject() {
        return isTimeTableSetForSubject;
    }

    public void setIsTimeTableSetForSubject(byte isTimeTableSetForSubject) {
        this.isTimeTableSetForSubject = isTimeTableSetForSubject;
    }

    public byte getIsTimeTableSetForDeptTermSection() {
        return isTimeTableSetForDeptTermSection;
    }

    public void setIsTimeTableSetForDeptTermSection(byte isTimeTableSetForDeptTermSection) {
        this.isTimeTableSetForDeptTermSection = isTimeTableSetForDeptTermSection;
    }

    public Result getDistinctDepartmentsForTimeTable() throws SQLException, ClassNotFoundException {
        return TimeTableDataOperations.getDistinctDepartmentsForTimeTable() ;
    }

    public Result getFreeClasses() throws SQLException, ClassNotFoundException
    {
         Result result = TimeTableDataOperations.getFreeClassesForGivenDepartmentTermSection(this.departmentTermSectionId);
         String timeTable[][] = new String[result.length][7];

         for(int i=0; i< result.length; i++)
         {
             timeTable[i][0] = result.records[i][0]; //id
             timeTable[i][1] = result.records[i][1]; //DTS id
             timeTable[i][2] = result.records[i][2]; //SDTS id
             timeTable[i][3] = result.records[i][3]; //day
             timeTable[i][4] = result.records[i][4]; //class
             timeTable[i][5] = result.records[i][5]; //type
             timeTable[i][6] = result.records[i][6]; //allocated
         }

         return result;
    }

    public int createTimeTable() throws SQLException, ClassNotFoundException {
        return TimeTableDataOperations.createTimeTable(this);

    }

    public Result getDepartmentTermsForTimeTable(long selectedDepartmentId) throws SQLException, ClassNotFoundException {
        return TimeTableDataOperations.getDepartmentTermsForTimeTable(selectedDepartmentId);
    }

    public Result getDepartmentTermsSectionsForTimeTable(long selectedDepartmentId, long selectedTermId) throws SQLException, ClassNotFoundException {
        return TimeTableDataOperations.getDepartmentTermsSectionsForTimeTable(selectedDepartmentId, selectedTermId);
    }

    public Result getTimeTable(long departmentId, long selectedTerm, long selectedSection) throws SQLException, ClassNotFoundException {
        return  TimeTableDataOperations.getTimeTable( departmentId,  selectedTerm,  selectedSection);
    }
    
    public Result getDistinctSubjectsTimeTable(long departmentId, long termId, long sectionId) throws SQLException, ClassNotFoundException {
        return  TimeTableDataOperations.getDistinctSubjectsTimeTable( departmentId,  termId,  sectionId);
    }
}
