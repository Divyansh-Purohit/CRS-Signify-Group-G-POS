/**
 * 
 */
package com.signify.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author dp201
 *
 */
public class StudentDAOImplementation implements StudentDAOInterface {

	public void register(String username, String password, String address) {
		int user_id = -1;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_s = null;
		PreparedStatement stmt_student_id = null;

		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "insert into user(username, password, address, role, doj) values(?,?,?,?,?)";
			String sql_student = "insert into student(studentid, batch, branch, isapproved, numCourses, semester) values(?,?,?,?,?,?)";
			String sql_student_id = "select user_id from user where username=" + "\"" + username + "\""
					+ " and password=" + "\"" + password + "\"";
			stmt = conn.prepareStatement(sql);
			stmt_s = conn.prepareStatement(sql_student);
			stmt_student_id = conn.prepareStatement(sql_student_id);

			String doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//			   stmt.setInt(1, Ids.userId);  
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, address);
			stmt.setString(4, "student");
			stmt.setDate(5, Date.valueOf(doj));
			stmt.executeUpdate();
			stmt.close();

			ResultSet rs = stmt_student_id.executeQuery(sql_student_id);

			while (rs.next()) {
				user_id = rs.getInt("user_id");
			}

			rs.close();
			stmt_student_id.close();

			stmt_s.setInt(1, user_id);
			stmt_s.setString(2, "NULL");
			stmt_s.setString(3, "NULL");
			stmt_s.setString(4, "NULL");
			stmt_s.setInt(5, 0);
			stmt_s.setInt(6, 5);
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

	public boolean getIsApprovedStatus(int student_id) {

		Connection conn = null;
		PreparedStatement stmt = null;

		String isapproved = "no";

		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select isapproved from student where studentid=" + student_id;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				isapproved = rs.getString("isapproved");
			}
			rs.close();
			stmt.close();
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
		return isapproved.equals("NULL") ? false : true;
	}

	public void addCourse(int student_id, String course_code) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_s = null;

		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "insert into student_course(student_id, course_code) values(?,?)";
//			String sql = "insert into student_course(student_id, course_code, grade) values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, student_id);
			stmt.setString(2, course_code);
//			stmt.setString(3, null);
			stmt.executeUpdate();
			stmt.close();
			
			
			String sql_s = "update student set numcourses= numcourses+1 where student_id =" + student_id;
			stmt_s = conn.prepareStatement(sql_s);
			
			stmt_s.execute(sql_s);
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
	
	public void dropCourse(int studentId, String courseId)
	{
		Connection conn = null;
		PreparedStatement stmt1 = null;
		
		
		try{
			   
			   conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			   String sql="DELETE FROM student_course WHERE course_code="+courseId+"and student_id="+studentId;
			   stmt1 = conn.prepareStatement(sql);

			   ResultSet rs = stmt1.executeQuery(sql);
			   String sql_1="UPDATE catalog SET numstudents=numstudents-1 WHERE course_code="+courseId;
			   stmt1 = conn.prepareStatement(sql_1);

			   ResultSet rs_1 = stmt1.executeQuery(sql_1);			   
			   rs_1.close();
			   stmt1.close();
			   conn.close();
			     			      
			}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt1 != null)
					stmt1.close();
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

	public void viewGrades(int student_id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select sc.grade,cat.coursename from student_course sc natural join catalog cat where sc.student_id="
					+ student_id;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs == null) {
				System.out.println("No courses found!");
			} else {
				while (rs.next()) {
					String coursename = rs.getString("course_code");
					String grade = rs.getString("grade");

					System.out.println("Course Name: " + coursename);
					System.out.println("Grade: " + grade);
				}
			}

			rs.close();
			stmt.close();
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

	@Override
	public void viewGrade(int studentId, String courseCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewRegisterCourses(int studentId) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void payFees(int studentId) {
		// TODO Auto-generated method stub
		
	}

}
