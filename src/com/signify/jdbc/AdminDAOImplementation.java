/**
 * 
 */
package com.signify.jdbc;
import com.signify.exception.*;
import com.signify.bean.*;
import com.signify.constants.SQLConstants;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author dp201
 *
 */
public class AdminDAOImplementation {

	public void addAdmin(Admin newAdmin) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_admin = null;
		
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			
			stmt = conn.prepareStatement(SQLConstants.REGISTER_USER);
			stmt.setString(1, newAdmin.getUserId());
			stmt.setString(2, newAdmin.getName());
			stmt.setString(3, newAdmin.getPassword());
			stmt.setString(4, newAdmin.getAddress());
			stmt.setDate(5, Date.valueOf(newAdmin.getDoj()));
			stmt.setInt(6, newAdmin.getRoleid());
			stmt.executeUpdate();
			stmt.close();

			stmt_admin = conn.prepareStatement(SQLConstants.REGISTER_ADMIN);
			stmt_admin.setString(1, newAdmin.getUserId());
			stmt_admin.setString(2, newAdmin.getName());
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

	public void addProfessor(Professor p) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_professor = null;

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);

			String sql = "insert into user (userid, username, password, address, doj, roleid) values(?,?,?,?,?,?)";
			stmt = conn.prepareStatement(SQLConstants.REGISTER_USER);
			stmt.setString(1, p.getUserId());
			stmt.setString(2, p.getName());
			stmt.setString(3, p.getPassword());
			stmt.setString(4, p.getAddress());
			stmt.setDate(5, Date.valueOf(p.getDoj()));
			stmt.setInt(6, p.getRoleid());
			stmt.executeUpdate();
			stmt.close();

			stmt_professor = conn.prepareStatement(SQLConstants.REGISTER_PROFESSOR);
			stmt_professor.setString(1, p.getUserId());
			stmt_professor.setString(2, p.getName());
			stmt_professor.setString(3, p.getDepartment());
			stmt_professor.setString(4, p.getDepartment());
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

	public void addCourse(Course c) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_professor = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);

			stmt = conn.prepareStatement(SQLConstants.INSERT_COURSEINCATALOG);
			stmt.setString(1, c.getCourseCode());
			stmt.setString(2, c.getName());
			stmt.setInt(3, c.getNumStudents());
			stmt.setString(4, c.getInstructor());
			stmt.setDouble(5, c.getFee());
			stmt.executeUpdate();
			stmt.close();

			stmt_professor = conn.prepareStatement(SQLConstants.UPDATE_COURSEOF_PROFESSOR);
			stmt_professor.setString(1, c.getCourseCode());
			stmt_professor.setString(2, c.getInstructor());
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
			stmt = conn.prepareStatement(SQLConstants.REMOVE_COURSE_FROM_CATALOG);
			stmt.setString(1, coursecode);
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

	public void assignProfessorToCourse(String professorid, String courseCode) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_course = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);

			stmt = conn.prepareStatement(SQLConstants.UPDATE_COURSEOF_PROFESSOR);
			stmt.setString(1, courseCode);
			stmt.setString(2, professorid);
			stmt.executeUpdate();
			stmt.close();

			stmt_course = conn.prepareStatement(SQLConstants.UPDATE_PROFESSORIN_CATALOG);
			stmt_course.setString(1, professorid);
			stmt_course.setString(2, courseCode);
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

	public Course viewCourseDetails(String courseCode) {
		Course c = new Course();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);

			stmt = conn.prepareStatement(SQLConstants.SELECT_CATALOG_WITH_COURSECODE);
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				c.setCourseCode(rs.getString("coursecode"));
				c.setName(rs.getString("coursename"));
				c.setNumStudents(rs.getInt("numstudents"));
				c.setInstructor(rs.getString("instructor"));
				c.setFee(rs.getDouble("coursefee"));
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
		return c;
	}

	public List<Course> viewCourses() {
		List<Course> cs = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);

			stmt = conn.prepareStatement(SQLConstants.SELECT_CATALOG);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Course c = new Course();
				c.setCourseCode(rs.getString("coursecode"));
				c.setName(rs.getString("coursename"));
				c.setNumStudents(rs.getInt("numstudents"));
				c.setInstructor(rs.getString("instructor"));
				c.setFee(rs.getDouble("coursefee"));
				cs.add(c);
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
		return cs;
	}

	public List<Student> listOfUnapprovedStudents() {
		List<Student> uas = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.SELECT_UNAPPROVED);
			stmt.setInt(1, 0);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Student c = new Student();
				c.setUserId(rs.getString("user_id"));
				c.setStudentid(rs.getString("student_id"));
				c.setName(rs.getString("username"));

				uas.add(c);
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
		return uas;
	}

	public void approveAllStudents() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.SET_ALLAPPROVED);
			stmt.setInt(1, 1);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (

		SQLException se) {
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

	public void approveStudentById(String studentid) {
		System.out.println(studentid);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.SET_APPROVEDBYID);
			stmt.setInt(1, 1);
			stmt.setString(2, studentid);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (

		SQLException se) {
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

	public List<Admin> viewAdmins() {
		List<Admin> la = new ArrayList<Admin>(); 
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.SELECT_ADMINS);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Admin a = new Admin();
				a.setUserId(rs.getString("adminid"));
				a.setName(rs.getString("adminname"));
				la.add(a);

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
		return la;
	}

	public List<Professor> viewProfessors() {
		List<Professor> lp = new ArrayList<Professor>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.SELECT_PROFESSORS);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Professor p = new Professor();
				p.setUserId(rs.getString("professorid"));
				p.setName(rs.getString("professorname"));
				p.setDesignation(rs.getString("designation"));
				p.setDepartment(rs.getString("department"));
				p.setCourseTaught(rs.getString("course"));
				lp.add(p);

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
		return lp;
	}

	public double calculateCpi(String studentid) {
		
		String grade;
		double cgpa = 0.0, cpi = 0.0;
		int n = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.SELECT_GRADES);
			stmt.setString(1, studentid);

			ResultSet rs = stmt.executeQuery();

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
