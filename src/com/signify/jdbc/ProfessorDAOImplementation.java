package com.signify.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.signify.bean.Student;

public class ProfessorDAOImplementation implements ProfessorDAOInterface {

	@Override
	public List<Integer> viewEnrolledStudents(int professorId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Integer> students = new ArrayList<Integer>();

		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select * from student_course inner join professor on student_course.course_code = professor.coursetaught where professor_id = "
					+ professorId;
			stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery(sql);
			Student st = new Student();
			while (rs.next()) {
				students.add(rs.getInt("student_id"));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
			} finally {
			}
		}
		
		return students;
	}

	@Override
	public void addGrades(int professorId, int studentId, String grade) {
		String coursecode = "";
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_s = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select coursetaught from professor where professor_id = ?";
			String sql_grade = "update student_course set grade = ? where student_id = ? and course_code = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery();
			
			stmt_s = conn.prepareStatement(sql_grade);
			while(rs.next()) {
				coursecode = rs.getString("coursetaught");
			}
			
			rs.close();
			stmt.close();
			
			stmt_s.setString(1, grade);
			stmt_s.setInt(2, studentId);
			stmt_s.setString(3, coursecode);
			stmt_s.executeUpdate();
			stmt_s.close();

			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}