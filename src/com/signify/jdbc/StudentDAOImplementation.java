/**
 * 
 */
package com.signify.jdbc;
import com.signify.bean.*;
import java.util.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author dp201
 *
 */
public class StudentDAOImplementation implements StudentDAOInterface {

	public void register(String username, String password, String address, int sem, String branch, String batch, String bg, String fname, String phnum, String doj) {
		int user_id = -1;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_id = null;
		PreparedStatement stmt_student = null;

		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "insert into user(username, password, address, doj, roleid) values(?,?,?,?,?)";
			
			stmt = conn.prepareStatement(sql); 
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, address);
			stmt.setDate(4, Date.valueOf(doj));
			stmt.setInt(5, 1);
			stmt.executeUpdate();
			stmt.close();
			
			String sql_id = "select userid from user where username=" + "\"" + username + "\"" + " and password=" + "\"" + password + "\"";
			stmt_id = conn.prepareStatement(sql_id);
			ResultSet rs = stmt_id.executeQuery(sql_id);
			while (rs.next()) {
				user_id = rs.getInt("userid");
			}
			rs.close();
			stmt_id.close();
			
			String sql_student = "insert into student(userid, studentid, studentname, semester, branch, batch, bloodgroup, fathername, phone, isapproved, numcourses) values(?,?,?,?,?,?,?,?,?,?,?)";
			stmt_student = conn.prepareStatement(sql_student);
			stmt_student.setInt(1, user_id);
			stmt_student.setInt(2, 5001);
			stmt_student.setString(3, username);
			stmt_student.setInt(4, sem);
			stmt_student.setString(5, branch);
			stmt_student.setString(6, batch);
			stmt_student.setString(7, bg);
			stmt_student.setString(8, fname);
			stmt_student.setString(9, phnum);
			stmt_student.setInt(10, 0);
			stmt_student.setInt(11, 0);
			stmt_student.executeUpdate();
			stmt_student.close();

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

	public boolean getIsApprovedStatus(int student_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		int isapproved = 0;

		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select isapproved from student where studentid=" + student_id;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				isapproved = rs.getInt("isapproved");
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
		return isapproved == 0 ? false : true;
	}
	
	public List<Course> getAvailableCourses()
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Course> ac = new ArrayList<Course>();
		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select coursecode, cousrename, instructor, 10-numstudents as seats_available from catalog where 10-seats_available > 0";
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Course c = new Course();
				c.setCourseCode(rs.getString("coursecode"));
				c.setName(rs.getString("coursename"));
				c.setInstructor(rs.getInt("instructor"));
				c.setSeatsAvailable(rs.getInt("seats_available"));
				
				ac.add(c);
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
		return ac;
	}
	
	public void addCourse(int student_id, String courseCode, int type)
	{
		Connection conn = null;
		PreparedStatement stmt_1 = null;
		PreparedStatement stmt_2 = null;
		PreparedStatement stmt_3 = null;		
		try {
			
			int num = -1, sem = -1;
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql_1 = "select semester, numcourses from student where studentid = ? and type = ?";
			stmt_1 = conn.prepareStatement(sql_1);
			stmt_1.setInt(1, student_id);
			stmt_1.setInt(1, type);
			ResultSet rs = stmt_1.executeQuery(sql_1);
			while(rs.next())
			{
				num = rs.getInt("numcourses");
				sem = rs.getInt("semester");
			}
			
			if(num > 4 && type == 1)
			{
				System.out.println("\nYou cannot register for more courses of current type!\n");
				return;
			}
			
			if(num > 2 && type == 1)
			{
				System.out.println("\nYou cannot register for more courses of current type!\n");
				return;
			}
			
			String sql_2 = "insert into registeredcourse (coursecode, semester, studentId, grade, type) values(?,?,?,?,?)";
			stmt_2 = conn.prepareStatement(sql_2);
			stmt_2.setString(1, courseCode);
			stmt_2.setInt(2, sem);
			stmt_2.setInt(3, student_id);
			stmt_2.setString(3, "NA");
			stmt_2.setInt(5, type);
			stmt_2.executeUpdate();
			stmt_2.close();
			
			
			String sql_3 = "update student set numcourses = numcourses+1 where studentid =" + student_id;
			stmt_3 = conn.prepareStatement(sql_3);			
			stmt_3.execute(sql_3);
			stmt_3.close();
			conn.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt_1 != null)
					stmt_1.close();
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
		PreparedStatement stmt = null;
		PreparedStatement stmt_1 = null;		
		
		try{
			   conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			   String sql="DELETE FROM registeredcourses where courseCode="+courseId+"and studentId="+studentId;
			   stmt = conn.prepareStatement(sql);
			   ResultSet rs = stmt.executeQuery(sql);
			   rs.close();
			   stmt.close();
			   
			   
			   String sql_1="update catalog SET numstudents=numstudents-1 WHERE coursecode="+courseId;
			   stmt_1 = conn.prepareStatement(sql_1);
			   ResultSet rs_1 = stmt_1.executeQuery(sql_1);			   
			   rs_1.close();
			   stmt_1.close();
			   conn.close();
			     			      
			}
		catch (SQLException se) {
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

	public List<Grades> viewGrades(int student_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Grades> grades = new ArrayList<Grades>(); 
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select rc.courseCode as coursecode, cat.coursename as coursename, rc.grade from registeredcourse rc natural join catalog cat where rc.studentId="	+ student_id  + " and type = 1";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs == null) {
				System.out.println("\nNo courses found!\n");
			} else {
				while (rs.next()) {
					String cc = rs.getString("coursecode");
					String cn = rs.getString("coursename");
					String grade = rs.getString("grade");

					Grades g = new Grades();
					g.setCourseCode(cc);
					g.setCourseName(cn);
					g.setGrade(grade);
					
					grades.add(g);
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
		return grades;
	}

	public String[] viewGrade(int studentId, String courseCode)
	{		
		Connection conn = null;
		PreparedStatement stmt = null;
		String[] arr = new String[4]; 
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select rc.courseCode as coursecode, cat.instructor as instructor, cat.coursename as coursename, rc.grade from registeredcourse rc natural join catalog cat where rc.studentId="	+ studentId  + " and type = 1 and cat.courseCode = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs == null) {
				System.out.println("\nNo course found!\n");
			} else {
				while (rs.next()) {
					arr[0] = rs.getString("coursecode");
					arr[1] = rs.getString("coursename");
					arr[2] = String.valueOf(rs.getInt("instructor"));
					arr[3] = rs.getString("grade");
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
		return arr;
	}

	public List<Course> viewRegisteredCourses(int studentId) 
	{	
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Course> rcourses = new ArrayList<Course>(); 
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select rc.courseCode, cat.coursename as coursename, cat.instructor as instructor, rc.type as type from registeredcourse rc natural join catalog cat where rc.studentId="+studentId;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs == null) {
				System.out.println("\nNo courses found!\n");
			} else {
				while (rs.next()) {
					String cc = rs.getString("coursecode");
					String cn = rs.getString("coursename");
					int type = rs.getInt("type");
					int instructor = rs.getInt("instructor");
					
					Course c = new Course();
					c.setCourseCode(cc);
					c.setName(cn);
					c.setType(type);
					c.setInstructor(instructor);
					rcourses.add(c);
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
		return rcourses;
	}

	public void payFees(int studentId)
	{	
//		TO BE DONE BY HIMANSHU
	}

}
