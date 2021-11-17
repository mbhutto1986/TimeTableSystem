package com.mbhutto.entity;


import com.mbhutto.database.SubjectDataOperations;

import java.sql.*;


public class Subject
{
	private long 	subjectId							;
	private String	subjectCode							;
	private String	subjectName							;
	private String  subjectShortName 					;
	private int     subjectTheoryMarks 	     			;
	private int     subjectTheoryClassesInWeek 			;
	private int     subjectPracticalMarks 	   			;
	private int     subjectPracticalClassesInWeek	    ;
	private ResultSet resultSet ;

    // Constructor first four fields
	public Subject(long subjectId, String subjectCode, String subjectName, String subjectShortName)  throws SQLException
	{
		this.subjectId = subjectId;
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.subjectShortName = subjectShortName;
	}

	// Constructor all  fields
	public Subject(long subjectId, String subjectCode, String subjectName, String subjectShortName, int subjectTheoryMarks, int subjectTheoryClassesInWeek, int subjectPracticalMarks, int subjectPracticalClassesInWeek) throws SQLException
	{
		this.subjectId = subjectId;
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.subjectShortName = subjectShortName;
		this.subjectTheoryMarks = subjectTheoryMarks;
		this.subjectTheoryClassesInWeek = subjectTheoryClassesInWeek;
		this.subjectPracticalMarks = subjectPracticalMarks;
		this.subjectPracticalClassesInWeek = subjectPracticalClassesInWeek;
	}

	public Subject()
	{

	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectShortName() {
		return subjectShortName;
	}

	public void setSubjectShortName(String subjectShortName) {
		this.subjectShortName = subjectShortName;
	}

	public int getSubjectTheoryMarks() {
		return subjectTheoryMarks;
	}

	public void setSubjectTheoryMarks(int subjectTheoryMarks) {
		this.subjectTheoryMarks = subjectTheoryMarks;
	}

	public int getSubjectTheoryClassesInWeek() {
		return subjectTheoryClassesInWeek;
	}

	public void setSubjectTheoryClassesInWeek(int subjectTheoryClassesInWeek) {
		this.subjectTheoryClassesInWeek = subjectTheoryClassesInWeek;
	}

	public int getSubjectPracticalMarks() {
		return subjectPracticalMarks;
	}

	public void setSubjectPracticalMarks(int subjectPracticalMarks) {
		this.subjectPracticalMarks = subjectPracticalMarks;
	}

	public int getSubjectPracticalClassesInWeek() {
		return subjectPracticalClassesInWeek;
	}

	public void setSubjectPracticalClassesInWeek(int subjectPracticalClassesInWeek) {
		this.subjectPracticalClassesInWeek = subjectPracticalClassesInWeek;
	}

	@Override
	public String toString()
	{
		return  subjectId +". " + subjectName ;

	}

	// Add Record Method call the addSubject method
    public long addRecord() throws SQLException, ClassNotFoundException {
		return (SubjectDataOperations.addSubject(this.subjectCode, this.subjectName, this.subjectShortName, this.subjectTheoryMarks,this.subjectTheoryClassesInWeek, this.subjectPracticalMarks, this.subjectPracticalClassesInWeek));
	}


	// delete Record Method call the deleteSubject method DELETE BY NAME
	public int deleteRecord() throws SQLException, ClassNotFoundException {
		return (SubjectDataOperations.deleteSubject(this.subjectName));
	}

	// Update Record Method call the updateSubject method     update BY ID
    public int updateRecord() throws SQLException, ClassNotFoundException {
		return (SubjectDataOperations.updateSubject(this.subjectId, this.subjectCode, this.subjectName, this.subjectShortName, this.subjectTheoryMarks,this.subjectTheoryClassesInWeek, this.subjectPracticalMarks, this.subjectPracticalClassesInWeek));
	}

	// search Record() Method call the searchSubject method of TeachersDataBase    SEARCH BY NAME
	protected Subject searchRecord() throws SQLException, ClassNotFoundException
	{
		String[] values  = SubjectDataOperations.searchSubject(this.subjectName);
		return new Subject( Long.parseLong(values[0]), values[1], values[2], values[3], Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]));
	}



	public Result getRecords() throws SQLException, ClassNotFoundException
	{
		return SubjectDataOperations.getSubjects();
	}


    public Result getSubjectDepartmentTerms() throws SQLException, ClassNotFoundException {

		return SubjectDataOperations.getSubjectsDepartmentsTerms(this.subjectId);
    }
}

