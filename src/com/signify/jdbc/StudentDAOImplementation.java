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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.signify.bean.Course;
import com.signify.bean.Grades;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.Payment;
import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.constants.SQLConstants;
import com.signify.utils.DBUtils;

/**
 * @author dp201
 *
 */
public class StudentDAOImplementation implements StudentDAOInterface {

	public void register(Student student) {

		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_student = null;
		try {
			
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.REGISTER_USER);
			String doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			stmt.setString(1, student.getUserId());
			stmt.setString(2, student.getName());
			stmt.setString(3, student.getPassword());
			stmt.setString(4, student.getAddress());
			stmt.setDate(5, Date.valueOf(doj));
			stmt.setInt(6, 1);
			stmt.executeUpdate();
			stmt.close();

			String sql_student = "insert into student(userid, studentid, studentname, branch, age, bloodgroup, fname, phnum, isapproved, numcourses) values(?,?,?,?,?,?,?,?,?,?)";
			stmt_student = conn.prepareStatement(sql_student);
			stmt_student.setString(1, student.getUserId());
			stmt_student.setString(2, student.getStudentId());
			stmt_student.setString(3, student.getName());
			stmt_student.setString(4, student.getBranch());
			stmt_student.setInt(5, student.getAge());
			stmt_student.setString(6, student.getBloodgroup());
			stmt_student.setString(7, student.getFname());
			stmt_student.setString(8, student.getPhnum());
			stmt_student.setInt(9, 0);
			stmt_student.setInt(10, 0);
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

	public String getStudentId(String userid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String student_id = "";

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select studentid from student where userid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				student_id = rs.getString("studentid");
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

	public int getIsApprovedStatus(String studentid) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int isapproved = 0;

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			String sql = "select isapproved from student where studentid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, studentid);
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
		return isapproved;
	}

	public List<Course> getAvailableCourses() {
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
				c.setInstructor(rs.getString("instructor"));
				c.setNumStudents(rs.getInt("numstudents"));
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

	public void addCourse(String studentid, String courseCode, int type) {
		Connection conn = null;
		PreparedStatement stmt_1 = null;
		PreparedStatement stmt_2 = null;
		PreparedStatement stmt_3 = null;
		int num = -1, sem = -1;

//		try {
//
//			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
//			String sql_1 = "insert into registeredcourse values(?,?,?,?,?)";
//			stmt_1 = conn.prepareStatement(sql_1);
//			stmt_1.setString(1, courseCode);
//			stmt_1.setInt(2, 8);
//			stmt_1.setString(3, studentid);
//			stmt_1.setString(4, "NA");
//			stmt_1.setInt(5, 1);
//			stmt_1.execute();
//			while(rs.next())
//			{
//				num = rs.getInt("numcourses");
//				sem = rs.getInt("semester");
//			}
//
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
//////			
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
//			stmt_1.close();
//			conn.close();
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (stmt_1 != null)
//					stmt_1.close();
//			} catch (SQLException se2) {
//			}
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//		}

	}

	public void dropCourse(String studentid, String courseId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_1 = null;
		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.DELETE_USER_COURSE);
			stmt.setString(1, courseId);
			stmt.setString(2, studentid);
			stmt.executeUpdate();
			stmt.close();

			String sql_1 = "update catalog SET numstudents=numstudents-1 WHERE coursecode=" + courseId;
			stmt_1 = conn.prepareStatement(SQLConstants.UPADTE_CATALOG_AFTER_STUDENT);
			stmt_1.setString(1, studentid);
			stmt_1.executeUpdate();
			stmt_1.close();
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

	public List<Grades> viewGrades(String studentid) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Grades> grades = new ArrayList<Grades>();
		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.VIEW_GRADES);
			stmt.setString(1, studentid);
			ResultSet rs = stmt.executeQuery();
			if (rs == null) {
				System.out.println("\nNO COURSES FOUND!\n");
			} else {
				while (rs.next()) {
					String cc = rs.getString("coursecode");
					String cn = rs.getString("coursename");
					String grade = rs.getString("grade");
					System.out.println(cc+" "+cn+" "+grade);
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

	public List<RegisteredCourse> viewRegisteredCourses(String studentid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<RegisteredCourse> rcourses = new ArrayList<RegisteredCourse>();
		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.VIEW_REGISTERED_COURSES);
			stmt.setString(1, studentid);
			ResultSet rs = stmt.executeQuery();
			if (rs == null) {
				System.out.println("\nNO COURSES FOUND!\n");
			} else {
				while (rs.next()) {
					String cc = rs.getString("coursecode");
					String cn = rs.getString("coursename");
//					double cf = rs.getDouble("coursefee");
					int type = rs.getInt("type");
					
					RegisteredCourse c = new RegisteredCourse();
					c.setCourseCode(cc);
					c.setCourseName(cn);
					c.setType(type);
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

	public List<Course> getFees(String studentid) {
		Connection conn = null;
		PreparedStatement stmt = null;
//		int total = 0;
		List<Course> courses = new ArrayList<Course>();
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.GET_FEES);
			stmt.setString(1, studentid);
			ResultSet rs = stmt.executeQuery();
			if (rs == null) {
				System.out.println("\nNO COURSES FOUND!\n");
			} else {
				System.out.println("\nCOURSE CODE\tCOURSE NAME\tCOURSE FEE\n");
				while (rs.next()) {
					String coursecode = rs.getString("coursecode");
					String coursename = rs.getString("coursename");
					int fee = rs.getInt("coursefee");
//					total += fee;
					Course c = new Course();
					c.setCourseCode(coursecode);
					c.setName(coursename);
					c.setFee(fee);
					courses.add(c);	
				}
			}

//			System.out.println("Total Fees : \t" + total + "\n");
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
		return courses;
	}

	@Override
	public void payFeesByCard(OnlinePayment onp, Payment p) {
		Connection conn = null;
		PreparedStatement stmt = null, stmt_s = null;
		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.PAYMENT_CARD);
			stmt.setString(1, p.getReferencedId());
			stmt.setString(2, p.getStudentId());
			stmt.setString(3, p.getMode());
			stmt.setString(4, "PAID");
			stmt.setDouble(5, p.getAmount());

			stmt_s = conn.prepareStatement(SQLConstants.PAYMENT_CARD_ONLINE);
			stmt_s.setString(1, onp.getReferencedId());
			stmt_s.setInt(2, onp.getCard());
			stmt_s.setString(3, onp.getCardNumber());
			stmt_s.setString(4, onp.getCardType());
			stmt_s.setString(5, onp.getModeOfTransfer());
			stmt_s.setString(6, onp.getAccountNumber());
			stmt_s.setString(7, onp.getIfscode());

			stmt.executeUpdate();
			stmt_s.execute();
			System.out.println("\nFEE PAID BY CARD\n");

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
	public void payFeesByNetBanking(OnlinePayment onp, Payment p)
	{
		Connection conn = null;
		PreparedStatement stmt = null, stmt_s = null;

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.PAYMENT_NETBANK);
			stmt.setString(1, p.getReferencedId());
			stmt.setString(2, p.getStudentId());
			stmt.setString(3, p.getMode());
			stmt.setString(4, "PAID");
			stmt.setDouble(5, p.getAmount());

			stmt_s = conn.prepareStatement(SQLConstants.PAYMENT_NETBANK_ONLINE);
			stmt_s.setString(1, onp.getReferencedId());
			stmt_s.setInt(2, onp.getCard());
			stmt_s.setString(3, onp.getCardNumber());
			stmt_s.setString(4, onp.getCardType());
			stmt_s.setString(5, onp.getModeOfTransfer());
			stmt_s.setString(6, onp.getAccountNumber());
			stmt_s.setString(7, onp.getIfscode());

			stmt.executeUpdate();
			stmt_s.execute();
			System.out.println("\nFEE PAID BY NET BANKING\n");

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
	public void payFeesByCheque(OfflinePayment ofp, Payment p) {
		Connection conn = null;
		PreparedStatement stmt = null, stmt_s = null;

		try {

			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.PAYMENT_CHEQUE);
			stmt.setString(1, p.getReferencedId());
			stmt.setString(2, p.getStudentId());
			stmt.setString(3, p.getMode());
			stmt.setString(4, "PAID");
			stmt.setDouble(5, p.getAmount());

			stmt_s = conn.prepareStatement(SQLConstants.PAYMENT_CHEQUE_OFFLINE);
			stmt_s.setString(1, ofp.getReferencedId());
			stmt_s.setInt(2, ofp.getCash());
			stmt_s.setInt(3, ofp.getChequeNumber());
			stmt_s.setString(4, ofp.getBankName());

			stmt.executeUpdate();
			stmt_s.execute();
			System.out.println("\nFEE PAID BY CHEQUE\n");

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
	public void payFeesByCash(OfflinePayment ofp, Payment p) {

		Connection conn = null;
		PreparedStatement stmt = null, stmt_s = null;

		try {
			conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
			stmt = conn.prepareStatement(SQLConstants.PAYMENT_CASH);
			stmt.setString(1, p.getReferencedId());
			stmt.setString(2, p.getStudentId());
			stmt.setString(3, p.getMode());
			stmt.setString(4, "PAID");
			stmt.setDouble(5, p.getAmount());

			stmt_s = conn.prepareStatement(SQLConstants.PAYMENT_CASH_OFFLINE);
			stmt_s.setString(1, ofp.getReferencedId());
			stmt_s.setInt(2, ofp.getCash());
			stmt_s.setInt(3, ofp.getChequeNumber());
			stmt_s.setString(4, ofp.getBankName());

			stmt.executeUpdate();
			stmt_s.execute();
			System.out.println("\nFEE PAID BY CASH\n");

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