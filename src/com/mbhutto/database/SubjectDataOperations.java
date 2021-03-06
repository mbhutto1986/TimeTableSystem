package com.mbhutto.database;

import com.mbhutto.entity.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


public final class SubjectDataOperations
{
    private static ResultSet resultSet ;

    public static long addSubject(String subjectCode, String subjectName, String subjectShortName, int subjectTheoryMarks, int subjectTheoryClassesInWeek, int subjectPracticalMarks, int subjectPracticalClassesInWeek)  throws SQLException, ClassNotFoundException

    {   long   id = -1;
        String insertQuery = "INSERT INTO subjects" +
                "(subject_code, subject_name, subject_short_name, subject_theory_marks, subject_theory_classes_in_week, subject_practical_marks, subject_practical_classes_in_week)" +
                " VALUES " +
                "(?, ?, ?, ?, ?, ?, ?)" ;

        PreparedStatement insertPreparedStatement = Connect.getConnection().prepareStatement(insertQuery , RETURN_GENERATED_KEYS);
        insertPreparedStatement.setString(1,subjectCode.trim())			;
        insertPreparedStatement.setString(2,subjectName.trim()) 	        ;
        insertPreparedStatement.setString(3,subjectShortName.trim()) 	    ;
        insertPreparedStatement.setInt(4,subjectTheoryMarks)            	;
        insertPreparedStatement.setInt(5,subjectTheoryClassesInWeek)        ;
        insertPreparedStatement.setInt(6,subjectPracticalMarks)             ;
        insertPreparedStatement.setInt(7,subjectPracticalClassesInWeek)     ;
        int inserted = insertPreparedStatement.executeUpdate()                           ;

        resultSet = insertPreparedStatement.getGeneratedKeys();
        if (resultSet.next())
        {
             id  = resultSet.getLong(1);
        }
        resultSet.close();

        return id;
    }

    public static void deptSubTerm(long departmentId, Long termId, long subjectId) throws SQLException, ClassNotFoundException
    {
        long   id = -1;

        String selectQuery = "SELECT id FROM departments_terms_sections " +
                "WHERE department_id = ? AND term_id = ?";
        PreparedStatement selectPreparedStatement = Connect.getConnection().prepareStatement(selectQuery);
        selectPreparedStatement.setLong(1, departmentId);
        selectPreparedStatement.setLong(2, termId);
        resultSet = selectPreparedStatement.executeQuery();


        PreparedStatement insertPreparedStatement ;
        while (resultSet.next())
        {
            id  = resultSet.getLong(1);

            String insertQuery  =  "INSERT INTO subjects_departments_terms_sections" +
                                  "(subject_id, departments_terms_sections_id, is_time_table_set)" +
                                   " VALUES "+
                                     "(?,?, ?)";
            insertPreparedStatement = Connect.getConnection().prepareStatement(insertQuery , RETURN_GENERATED_KEYS);
            insertPreparedStatement.setLong(1,subjectId);
            insertPreparedStatement.setLong(2,id);
            insertPreparedStatement.setInt(3, 0);
            insertPreparedStatement.executeUpdate();

        }
        selectPreparedStatement.close();
        resultSet.close();
    }


    public static int deleteSubject(String subjectName) throws SQLException, ClassNotFoundException
    {
        String query = "DELETE FROM subjects WHERE  subject_name = ?";
        PreparedStatement deletePreparedStatement = Connect.getConnection().prepareStatement(query);
        deletePreparedStatement.setString(1, subjectName); ;
        System.out.println(deletePreparedStatement)         ;
        int rows = deletePreparedStatement.executeUpdate() 	;
        deletePreparedStatement.close()						;
        return (rows);
    }

    public static int updateSubject(long subjectId, String subjectCode, String subjectName, String subjectShortName, int subjectTheoryMarks, int subjectTheoryClassesInWeek, int subjectPracticalMarks, int subjectPracticalClassesInWeek)  throws SQLException, ClassNotFoundException {
        String query = "UPDATE subjects " +
                "SET " +
                "subject_code = ?, " +
                "subject_name = ?, " +
                "subject_short_name = ?, " +
                "subject_theory_marks = ?, " +
                "subject_theory_classes_in_week = ?, " +
                "subject_practical_marks = ?, " +
                "subject_practical_classes_in_week = ? " +
                "WHERE id = ?" ;

        PreparedStatement updatePreparedStatement = Connect.getConnection().prepareStatement(query);
        updatePreparedStatement.setString(1, subjectCode.trim());
        updatePreparedStatement.setString(2, subjectName.trim());
        updatePreparedStatement.setString(3, subjectShortName.trim());
        updatePreparedStatement.setInt(4, subjectTheoryMarks);
        updatePreparedStatement.setInt(5, subjectTheoryClassesInWeek);
        updatePreparedStatement.setInt(6, subjectPracticalMarks);
        updatePreparedStatement.setInt(7, subjectPracticalClassesInWeek);
        updatePreparedStatement.setLong(8, subjectId);

        int rows = updatePreparedStatement.executeUpdate() 										;
        updatePreparedStatement.close()															;
        return (rows);
    }

    // Method that search subject record in subject table     Search by name
    public static String[] searchSubject(String SubjectName) throws SQLException, ClassNotFoundException
    {

        PreparedStatement searchPreparedStatement = Connect.getConnection().prepareStatement(
                "SELECT * FROM subjects " +
                        "WHERE subject_name = ?");

        searchPreparedStatement.setString(1, SubjectName)			;
        ResultSet resultSet = searchPreparedStatement.executeQuery();
        resultSet.next() ;

        String[] values = new String[8];

        values[0] = String.valueOf(resultSet.getLong(1))	                ;
        values[1] = resultSet.getString(2)					            ;
        values[2] = resultSet.getString(3)					            ;
        values[3] = resultSet.getString(4)					            ;
        values[4] = String.valueOf(resultSet.getInt(5))	                ;
        values[5] = String.valueOf(resultSet.getInt(6))					;
        values[6] = String.valueOf(resultSet.getInt(7))					;
        values[7] = String.valueOf(resultSet.getInt(8))					;

        resultSet.close() ;
        searchPreparedStatement.close();
        return values ;
    }

    public static Result getSubjectsDepartmentsTerms(Long subjectId) throws SQLException, ClassNotFoundException {
        Result result;
        String subjectsDepartmentsTermsQuery = "SELECT dts.department_id, dts.term_id, dts.section_id FROM subjects_departments_terms_sections sdts " +
                "        INNER JOIN departments_terms_sections dts ON dts.id = sdts.departments_terms_sections_id " +
                "        WHERE sdts.subject_id = ?";
        PreparedStatement subjectsDepartmentsTermsPreparedStatement = Connect.getConnection().prepareStatement(subjectsDepartmentsTermsQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        subjectsDepartmentsTermsPreparedStatement.setLong(1, subjectId)			;
        ResultSet resultSet = subjectsDepartmentsTermsPreparedStatement.executeQuery();
        String records[][];
        int i = 0;

        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            result = new Result(totalRecords);
            System.out.println(totalRecords);
            resultSet.beforeFirst();
            records = new String[totalRecords][2];

            while (resultSet.next())
            {
                records[i][0] = String.valueOf(resultSet.getLong(1));
                records[i][1] = String.valueOf(resultSet.getLong(2));
                i++;
            }
            result.setRecords(records);
        } else
        {
            result = new Result(0);
        }
        resultSet.close();
        subjectsDepartmentsTermsPreparedStatement.close();
        return result;
    }

    public static Result getSubjects() throws SQLException, ClassNotFoundException
    {
        Result result;
        String query = "SELECT * FROM subjects";
        PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = preparedStatement.executeQuery();
        String records[][];
        int i = 0;

        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            result = new Result(totalRecords);
            System.out.println(totalRecords);
            resultSet.beforeFirst();
            records = new String[totalRecords][8];

            while (resultSet.next())
            {
                records[i][0] = String.valueOf(resultSet.getLong(1));
                records[i][1] = resultSet.getString(2);
                records[i][2] = resultSet.getString(3);
                records[i][3] = resultSet.getString(4);
                records[i][4] = String.valueOf(resultSet.getInt(5));
                records[i][5] = String.valueOf(resultSet.getInt(6));
                records[i][6] = String.valueOf(resultSet.getInt(7));
                records[i][7] = String.valueOf(resultSet.getInt(8));
                i++;
            }
            result.setRecords(records);
        } else
        {
            result = new Result(0);
        }
        resultSet.close();
        preparedStatement.close();
        return result;
    }
}