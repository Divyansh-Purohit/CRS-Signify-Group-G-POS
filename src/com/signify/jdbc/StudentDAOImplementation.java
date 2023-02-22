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
import java.util.*;
import java.util.UUID;

import com.signify.bean.Course;
import com.signify.bean.Grades;

/**
 * @author dp201
 *
 */
public class StudentDAOImplementation implements StudentDAOInterface, StudentPaymentInterface {

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
	
	public int getStudentId(int userId)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		int student_id = 0;

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select studentid from student where userid=?";			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				student_id = rs.getInt("studentid");
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

		return student_id;
	}

	public int getIsApprovedStatus(int student_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		int isapproved = 0;

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select isapproved from student where studentid=?";			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, student_id);
			ResultSet rs = stmt.executeQuery();
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
		System.out.println(isapproved);
		return isapproved;
	}
	
	public List<Course> getAvailableCourses()
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Course> ac = new ArrayList<Course>();
		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select coursecode, coursename, instructor, numstudents from catalog where numstudents < 10";
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Course c = new Course();
				c.setCourseCode(rs.getString("coursecode"));
				c.setName(rs.getString("coursename"));
				c.setInstructor(rs.getInt("instructor"));
				c.setSeatsAvailable(rs.getInt("numstudents"));
				c.setSeatsAvailable(10-c.getSeatsAvailable());
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
//			String sql_1 = "select semester, numcourses from catalog where studentid = ? and coursecode = ?";
			String sql_1 = "insert into registeredcourse values(?,?,?,?,?)";
			stmt_1 = conn.prepareStatement(sql_1);
			stmt_1.setString(1, courseCode);
			stmt_1.setInt(2, 8);
			stmt_1.setInt(3, student_id);
			stmt_1.setString(4, "NA");
			stmt_1.setInt(5, 1);
			stmt_1.execute();
//			while(rs.next())
//			{
//				num = rs.getInt("numcourses");
//				sem = rs.getInt("semester");
//			}
			
//			if(num > 4 && type == 1)
//			{
//				System.out.println("\nYou cannot register for more courses of current type!\n");
//				return;
//			}
//			
//			if(num > 2 && type == 1)
//			{
//				System.out.println("\nYou cannot register for more courses of current type!\n");
//				return;
//			}
////			
//			String sql_2 = "insert into registeredcourse (coursecode, semester, studentId, grade, type) values(?,?,?,?,?)";
//			stmt_2 = conn.prepareStatement(sql_2);
//			stmt_2.setString(1, courseCode);
//			stmt_2.setInt(2, sem);
//			stmt_2.setInt(3, student_id);
//			stmt_2.setString(3, "NA");
//			stmt_2.setInt(5, type);
//			stmt_2.executeUpdate();
//			stmt_2.close();
//			
//			
//			String sql_3 = "update student set numcourses = numcourses+1 where studentid =" + student_id;
//			stmt_3 = conn.prepareStatement(sql_3);			
//			stmt_3.execute(sql_3);
			stmt_1.close();
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
	public int getFees(int studentId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int total = 0;

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select catalog.coursecode as coursecode , coursename , coursefee from registeredcourse inner join catalog on registeredcourse.coursecode = catalog.coursecode where studentId=\""
					+ studentId + "\"";
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			if (rs == null) {
				System.out.println("No courses found!");
			} else {
				System.out.println("Course Code\t CourseName\t Course fee");
				while (rs.next()) {
					String coursecode = rs.getString("coursecode");
					String coursename = rs.getString("coursename");
					Integer fee = rs.getInt("coursefee");
					total += fee;
					System.out.println(coursecode + "\t" + coursename + "\t" + fee);
				}
			}

			System.out.println("Total Fees : \t" + total + "\n");
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
		return total;
	}

	@Override
	public void payFeesByCard(Integer studentId, String cardNumber, String cardType, int amount) {
		Connection conn = null;
		PreparedStatement stmt = null, stmt_s = null;

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "insert into payment(referenceid , studentidfk , modeofpayment , status , amount) values(? , ? , ? , ? , ?)";
			String sql_s = "insert into payment_online(referenceidfk , card , cardnumber , cardtype , modeoftransfer , accountnumber , ifscode) values(? , ? , ? , ? , ? , ? , ?)";

			stmt = conn.prepareStatement(sql);
			String refId = UUID.randomUUID().toString();
			stmt.setString(1, refId);
			stmt.setInt(2, studentId);
			stmt.setString(3, "online");
			stmt.setString(4, "Paid");
			stmt.setInt(5, amount);

			stmt_s = conn.prepareStatement(sql_s);
			stmt_s.setString(1, refId);
			stmt_s.setInt(2, 1);
			stmt_s.setString(3, cardNumber);
			stmt_s.setString(4, cardType);
			stmt_s.setString(5, "NA");
			stmt_s.setString(6, "NA");
			stmt_s.setString(7, "NA");

			stmt.executeUpdate();
			stmt_s.execute();
			System.out.println("Fee paid by CARD");

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
	public void payFeesByNetBanking(Integer studentId, String modeOfTransfer, String accountNumber, String ifscCode,
			int amount) {
		Connection conn = null;
		PreparedStatement stmt = null, stmt_s = null;

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "insert into payment(referenceid , studentidfk , modeofpayment , status , amount) values(? , ? , ? , ? , ?)";
			String sql_s = "insert into payment_online(referenceidfk , card , cardnumber , cardtype , modeoftransfer , accountnumber , ifscode) values(? , ? , ? , ? , ? , ? , ?)";

			stmt = conn.prepareStatement(sql);
			String refId = UUID.randomUUID().toString();
			stmt.setString(1, refId);
			stmt.setInt(2, studentId);
			stmt.setString(3, "online");
			stmt.setString(4, "Paid");
			stmt.setInt(5, amount);

			stmt_s = conn.prepareStatement(sql_s);
			stmt_s.setString(1, refId);
			stmt_s.setInt(2, 0);
			stmt_s.setString(3, "NA");
			stmt_s.setString(4 , "NA");
			stmt_s.setString(5 , modeOfTransfer);
			stmt_s.setString(6, accountNumber);
			stmt_s.setString(7, ifscCode);

			stmt.executeUpdate();
			stmt_s.execute();
			System.out.println("Fee paid by NETBANKING");

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
	public void payFeesByCheque(Integer studentId, String bankName, String chequeNumber, int amount) {
		Connection conn = null;
		PreparedStatement stmt = null, stmt_s = null;
		
		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "insert into payment(referenceid , studentidfk , modeofpayment , status , amount) values(? , ? , ? , ? , ?)";
			String sql_s = "insert into payment_offline(referenceid , cash , chequenumber , bankname) values(? , ? , ? , ?)";

			stmt = conn.prepareStatement(sql);
			String refId = UUID.randomUUID().toString();
			stmt.setString(1, refId);
			stmt.setInt(2, studentId);
			stmt.setString(3, "offline");
			stmt.setString(4, "Paid");
			stmt.setInt(5, amount);

			stmt_s = conn.prepareStatement(sql_s);
			stmt_s.setString(1, refId);
			stmt_s.setInt(2, 0);
			stmt_s.setString(3, chequeNumber);
			stmt_s.setString(4, bankName);

			stmt.executeUpdate();
			stmt_s.execute();
			System.out.println("Fee paid by CHEQUE");

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
	public void payFeesByCash(Integer studentId, int amount) {
		Connection conn = null;
		PreparedStatement stmt = null, stmt_s = null;

		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "insert into payment(referenceid , studentidfk , modeofpayment , status , amount) values(? , ? , ? , ? , ?)";
			String sql_s = "insert into payment_offline(referenceid , cash , chequenumber , bankname) values(? , ? , ? , ?)";

			stmt = conn.prepareStatement(sql);
			String refId = UUID.randomUUID().toString();
			stmt.setString(1, refId);
			stmt.setInt(2, studentId);
			stmt.setString(3, "offline");
			stmt.setString(4, "Paid");
			stmt.setInt(5, amount);

			stmt_s = conn.prepareStatement(sql_s);
			stmt_s.setString(1, refId);
			stmt_s.setInt(2, 1);
			stmt_s.setString(3, "NA");
			stmt_s.setString(4, "NA");

			stmt.executeUpdate();
			stmt_s.execute();
			System.out.println("Fee paid by CASH");

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
}