package com.mbhutto.entity;

import com.mbhutto.database.DepartmentDataOperations;

import java.sql.* ;

public class Department
{
	private long 	departmentID			;
	private String	departmentName	   		;
	private String  departmentShortName 	;
	private	int	    departmentTerms		    ;
	private int  	departmentSections		;

	public Department()
	{

	}

	public Department(long departmentID)
	{
		this.departmentID = departmentID;
	}

	public Department(String departmentName)
	{
		this.departmentName = departmentName;
	}

	public Department(long departmentID, String departmentName, String departmentShortName, int departmentTerms, int departmentSections)
	{
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.departmentShortName = departmentShortName;
		this.departmentTerms = departmentTerms;
		this.departmentSections = departmentSections;
	}

	public long getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(long departmentID) {
		this.departmentID = departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentShortName() {
		return departmentShortName;
	}

	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
	}

	public int getDepartmentTerms() {
		return departmentTerms;
	}

	public void setDepartmentTerms(int departmentTerms) {
		this.departmentTerms = departmentTerms;
	}

	public int getDepartmentSections() {
		return departmentSections;
	}

	public void setDepartmentSections(int departmentSections) {
		this.departmentSections = departmentSections;
	}


	@Override
	public String toString()
	{
		return departmentID + ". " + departmentName ;
	}

	// Add Record Method call the add Departement method
    public long addRecord() throws SQLException, ClassNotFoundException {
		return (DepartmentDataOperations.addDepartment(this.departmentName, this.departmentShortName,this.departmentTerms,this.departmentSections));
	}


	// delete Record Method call the deleteDepartement method
	public int deleteRecord() throws SQLException, ClassNotFoundException {
		return (DepartmentDataOperations.deleteDepartment(this.departmentName));
	}

	protected Department searchRecord() throws SQLException, ClassNotFoundException
	{
		String[] values  = DepartmentDataOperations.searchDepartment(this.departmentName);
		return new Department(
				Long.parseLong(values[0]),
				values[1],
				values[2],
				Integer.parseInt(values[3]),
				Integer.parseInt(values[4]));
	}

	public Result getRecords() throws SQLException, ClassNotFoundException
	{


		return DepartmentDataOperations.viewDepartments();
		/*if(result.length != 0)
		Department department;
		 for(int i=0; i < departments.length; i++)
		 {
		 	department = new Department(
					Long.parseLong(departments[i][0]),
					departments[i][1],
					departments[i][2],
					Integer.parseInt(departments[i][3]),
					Integer.parseInt(departments[i][4])
			);

		 	departmentArrayList.add(department);
		 }

		Iterator itr=departmentArrayList.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
*/
	}





	public int updateRecord() throws SQLException, ClassNotFoundException
	{
		return (DepartmentDataOperations.updateDepartment(this.departmentID, this.departmentName, this.departmentShortName,this.departmentTerms,this.departmentSections));
	}
}

