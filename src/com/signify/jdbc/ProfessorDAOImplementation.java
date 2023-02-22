package com.signify.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			String sql = "select * from registeredcourse inner join professor on registeredcourse.courseCode = professor.course where professorid = "+ professorId;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Student st = new Student();
			while (rs.next()) {
				students.add(rs.getInt("studentId"));
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
		grade = grade.toUpperCase();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select course from professor where professorid = ?";
			String sql_grade = "update registeredcourse set grade = ? where studentId = ? and courseCode = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery();
			
			stmt_s = conn.prepareStatement(sql_grade);
			while(rs.next()) {
				coursecode = rs.getString("course");
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