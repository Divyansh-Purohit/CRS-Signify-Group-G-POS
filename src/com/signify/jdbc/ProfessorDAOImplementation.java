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
	public List<Student> viewEnrolledStudents(int professorId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Student> students = new ArrayList<Student>();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select * from student_course inner join professor on student.course_code = professor.coursetaught where professor_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery(sql);
			Student st = new Student();
			while (rs.next()) {
				st.setStudentId(rs.getInt("user_id"));
				students.add(st);
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
	public void addGrades(int professorId , int studentId , String grade) {
		int coursetaught = -1;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_s = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select coursetaught from professor where professor_id = ?";
			String sql_grade = "insert into student_course(grade) values(?)";
			stmt = conn.prepareStatement(sql);
			stmt_s = conn.prepareStatement(sql_grade);

			stmt.setInt(1, professorId);
			
			ResultSet rs = stmt_s.executeQuery(sql_grade);

			while (rs.next()) {
				coursetaught = rs.getInt("user_id");
			}

			rs.close();
			stmt.close();
			
			stmt_s.setInt(1, coursetaught);
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
