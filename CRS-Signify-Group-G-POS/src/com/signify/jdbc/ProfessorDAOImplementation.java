package com.signify.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.signify.bean.Student;
import com.signify.constants.SQLConstants;
import com.signify.exception.ProfessorNotTeachingException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.utils.DBUtils;

public class ProfessorDAOImplementation implements ProfessorDAOInterface {
	/**
	 * Method to view list of enrolled students
	 */

	public List<Student> viewEnrolledStudents(String professorId) throws ProfessorNotTeachingException {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_1 = null;
		List<Student> students = new ArrayList<Student>();
		try {

			conn = DBUtils.getConnection();

			stmt_1 = conn.prepareStatement(SQLConstants.IS_PROFESSOR_ASSIGNED);
			stmt_1.setString(1, professorId);
			ResultSet rs1 = stmt_1.executeQuery();
			while (rs1.next()) {
				if (rs1.getString("course") == null) {
					throw new ProfessorNotTeachingException(professorId);
				}
			}
			stmt_1.close();

			stmt = conn.prepareStatement(SQLConstants.VIEW_ENROLLED);
			stmt.setString(1, professorId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Student s = new Student();
				s.setStudentId(rs.getString("studentid"));
				students.add(s);
			}

			rs.close();
			stmt.close();

		} catch (SQLException se) {
			throw new ProfessorNotTeachingException(professorId);
		} catch (ProfessorNotTeachingException e) {
			throw new ProfessorNotTeachingException(professorId);
		}
		return students;
	}
	/**
	 * Method to add grades
	 */
	@Override
	public void addGrades(String professorId, String studentid, String grade)
			throws ProfessorNotTeachingException, StudentNotRegisteredException {
		String coursecode = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_s = null;
		grade = grade.toUpperCase();
		try {

			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.SELECT_COURSE);
			stmt.setString(1, professorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				coursecode = rs.getString("course");
				if (coursecode == null) {
					throw new ProfessorNotTeachingException(professorId);
				}
			}

			rs.close();
			stmt.close();

			stmt_s = conn.prepareStatement(SQLConstants.ADD_GRADES);
			stmt_s.setString(1, grade);
			stmt_s.setString(2, studentid);
			stmt_s.setString(3, coursecode);
			int row = stmt_s.executeUpdate();
			if (row == 0) {
				throw new StudentNotRegisteredException(studentid);
			}
			stmt_s.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ProfessorNotTeachingException e) {
			throw new ProfessorNotTeachingException(professorId);
		} catch (StudentNotRegisteredException e) {
			throw new StudentNotRegisteredException(studentid);
		}
	}
}