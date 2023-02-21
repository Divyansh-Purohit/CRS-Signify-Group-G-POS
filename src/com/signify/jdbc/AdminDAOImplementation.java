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
import java.util.Scanner;

/**
 * @author dp201
 *
 */
public class AdminDAOImplementation {
	
	public void addAdmin(String username, String password, String address, String doj) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_id = null;
		PreparedStatement stmt_admin = null;
		int id = -1;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			
			String sql = "insert into user (username, password, address, doj, roleid) values(?,?,?,?,?)";
						
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, address);
			stmt.setDate(4, Date.valueOf(doj));
			stmt.setInt(5, 2);
			stmt.executeUpdate();
			stmt.close();
			
			String sql_id = "select userid from user where username=" + "\"" + username + "\"" + " and password=" + "\"" + password + "\"";
			stmt_id = conn.prepareStatement(sql_id);
			ResultSet rs = stmt_id.executeQuery(sql_id);
			while (rs.next()) {
				id = rs.getInt("userid");
			}
			stmt_id.close();

			String sql_admin = "insert into admin values(?,?)";
			stmt_admin = conn.prepareStatement(sql_admin);
			stmt_admin.setInt(1, id);
			stmt_admin.setString(2, username);
			stmt_admin.executeUpdate();
			stmt_admin.close();

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

	public void addProfessor(String username, String password, String address, String designation, String department, String doj) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_id = null;
		PreparedStatement stmt_professor = null;
		int id = -1;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			
			String sql = "insert into user (username, password, address, doj, roleid) values(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);		
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, address);
			stmt.setDate(4, Date.valueOf(doj));
			stmt.setInt(5, 3);
			stmt.executeUpdate();
			stmt.close();

			String sql_id = "select userid from user where username=" + "\"" + username + "\"" + " and password="+"\"" + password + "\"";
			stmt_id = conn.prepareStatement(sql_id);
			ResultSet rs = stmt_id.executeQuery(sql_id);
			while (rs.next()) {
				id = rs.getInt("userid");
			}
			stmt_id.close();
			
	
			String sql_professor = "insert into professor values(?,?,?,?,?)";
			stmt_professor = conn.prepareStatement(sql_professor);
			stmt_professor.setInt(1, id);
			stmt_professor.setString(2, username);
			stmt_professor.setString(3, department);
			stmt_professor.setString(4, designation);
			stmt_professor.setString(5, null);
			stmt_professor.executeUpdate();
			stmt_professor.close();

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

	public void addCourse(String courseCode, String courseName, int instructor, int coursefee) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_professor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			
			String sql = "insert into catalog (coursecode, coursename, numstudents, instructor, coursefee) values(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseCode);
			stmt.setString(2, courseName);
			stmt.setInt(3, 0);
			stmt.setInt(4, instructor);
			stmt.setInt(5, coursefee);
			stmt.executeUpdate();
			stmt.close();
			
			String sql_professor = "update professor set course = ? where professorid = ?";
			stmt_professor = conn.prepareStatement(sql_professor);
			stmt_professor.setString(1, courseCode);
			stmt_professor.setInt(2, instructor);
			stmt_professor.executeUpdate();
			stmt_professor.close();
			
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

	public void removeCourse(String coursecode) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "delete from catalog where coursecode=" + "\"" + coursecode + "\"";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
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

	public void assignProfessorToCourse(int professor_id, String courseCode) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_course = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			
			String sql = "update professor set course=? where professor_id=" + professor_id;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseCode);
			stmt.executeUpdate();
			stmt.close();
			
			String sql_course = "update catalog set instructor=? where coursecode=" + "\"" + courseCode + "\"";
			stmt_course = conn.prepareStatement(sql_course);
			stmt_course.setInt(1, professor_id);
			stmt_course.close();

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

	public void viewCourseDetails(String courseCode) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			
			String sql = "select * from catalog where coursecode = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("Course Code  Course Name  Students Enrolled  Instructor Code  Course Fees");
			System.out.println("=========================================================================");		
			while (rs.next()) {
				String cc = rs.getString("coursecode");
				String cn = rs.getString("coursename");
				int ns = rs.getInt("numstudents");
				int ic = rs.getInt("instructor");
				int cf = rs.getInt("coursefee");

				System.out.println(cc+"\t"+cn+"\t"+ns+"\t"+ic+"\t"+cf);
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
		System.out.println();
	}
	
	public void viewCourses()
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			
			String sql = "select * from catalog";
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("Course_Code  Course_Name  Students_Enrolled  Instructor_Code  Course_Fees");
			System.out.println("=========================================================================");		
			while (rs.next()) {
				String cc = rs.getString("coursecode");
				String cn = rs.getString("coursename");
				int ns = rs.getInt("numstudents");
				int ic = rs.getInt("instructor");
				int cf = rs.getInt("coursefee");

				System.out.println(cc+"\t"+cn+"\t"+ns+"\t"+ic+"\t"+cf);
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
		System.out.println();
	}

	public void approveStudent() {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_1 = null;
		PreparedStatement stmt_2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select u.user_id as user_id, u.username as username from user u inner join Student s on u.user_id = s.studentid where s.isapproved=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "NULL");

			System.out.println("\nUser ID\tUsername\n");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int u_id = rs.getInt("user_id");
				String u_name = rs.getString("username");

				System.out.println(u_id + "\t" + u_name);
			}
			rs.close();
			stmt.close();

			System.out.println(
					"\nPRESS 1 TO APPROVE ALL STUDENTS\nPRESS 2 TO APPROVE STUDENT BY ID\nPRESS 3 TO GO BACK\n");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				String sql_1 = "update student set isapproved=?";
				stmt_1 = conn.prepareStatement(sql_1);
				stmt_1.setString(1, "Yes");
				stmt_1.executeUpdate();
				stmt_1.close();
				break;
			}
			case 2: {
				System.out.print("\nEnter Student ID to Approve: ");
				int s_id = sc.nextInt();
				String sql_2 = "update student set isapproved=? where studentid=" + s_id;
				stmt_2 = conn.prepareStatement(sql_2);
				stmt_2.setString(1, "Yes");
				stmt_2.executeUpdate();
				stmt_2.close();
				break;
			}
			case 3: {
				return;
			}
			default: {
				System.out.println("\nInvalid Input Received!\n");
				break;
			}
			}

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

	public void viewAdmins() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select * from admin";
			stmt = conn.prepareStatement(sql);

			System.out.println("\nAdmin ID\tAdmin Username\n");
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int u_id = rs.getInt("admin_id");
				String u_name = rs.getString("username");

				System.out.println(u_id + "\t" + u_name);
			}
			System.out.println();

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

	public void viewProfessors() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select * from professor";
			stmt = conn.prepareStatement(sql);

			System.out.println("\nProfessor ID\tProfessor Username\tDesignation\tDepartment\tCourse Taught\n");
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int u_id = rs.getInt("professor_id");
				String u_name = rs.getString("name");
				String designation = rs.getString("designation");
				String department = rs.getString("department");
				String courseCode = rs.getString("coursetaught");

				System.out.println(u_id + "\t" + u_name + "\t" + designation + "\t" + department + "\t" + courseCode);
			}
			System.out.println();
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

	public double calculateCpi(int studentId) {
		String grade;
		double cgpa = 0.0, cpi = 0.0;
		int n=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			Class.forName("com.mysql.jc.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select grade from student_course where student_id=" + studentId;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				grade = rs.getString("grade");
				n = n + 1;
				if (grade.equals("A+"))
					cgpa += 10.0;
				else if (grade.equals("A"))
					cgpa += 9.5;
				else if (grade.equals("A-"))
					cgpa += 8.5;
				else if (grade.equals("B"))
					cgpa += 8.0;
				else if (grade.equals("B-"))
					cgpa += 7.5;
				else if (grade.equals("C"))
					cgpa += 7.0;
				else if (grade.equals("C-"))
					cgpa += 6.5;
				else if (grade.equals("D"))
					cgpa += 6.0;
				else
					cgpa += 0;
			}

			cpi = cgpa / n;
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
		return cpi;
	}
}
