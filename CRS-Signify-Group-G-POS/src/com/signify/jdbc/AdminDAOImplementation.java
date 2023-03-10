/**

	* This class implements the AdminDAOInterface and provides functionality for
	  adding Admin, Professor, Course, and assigning Professor to Course in the
	  database.
*/
package com.signify.jdbc;

import com.signify.exception.*;
import com.signify.utils.DBUtils;
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
public class AdminDAOImplementation implements AdminDAOInterface {
/**
	* This method adds a new admin to the database.
	* 
	* @param newAdmin the Admin object to be added to the database
	* @throws UserAlreadyExistException if the user already exists in the database
 */

	public void addAdmin(Admin newAdmin) throws UserAlreadyExistException {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_admin = null;

		try {
			conn = DBUtils.getConnection();

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

			;
		} catch (SQLException se) {
			throw new UserAlreadyExistException(newAdmin.getUserId());
		}

	}
/**
	* This method adds a new professor to the database.
	* 
	* @param p the Professor object to be added to the database
	* @throws ProfessorNotAddedException  if the professor could not be added to the database
	* @throws UserAlreadyExistException  if the user already exists in the database
 */

	public void addProfessor(Professor p) throws ProfessorNotAddedException, UserAlreadyExistException {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_professor = null;

		try {
			conn = DBUtils.getConnection();

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

			;
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
/**

	* Adds a course to the catalog and updates the professor's record if the course does not already exist
	*
	* @param c the course object to be added to the catalog
	* @throws AddCourseException if an error occurs while adding the course to the catalog
	* @throws ProfessorNotFoundException if the specified instructor is not found in the database
	* @throws CourseFoundException if the specified course already exists in the catalog
*/
	public void addCourse(Course c) throws AddCourseException, ProfessorNotFoundException, CourseFoundException{
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_professor = null;
		PreparedStatement stmt_get_professor = null;
		PreparedStatement stmt_get_course = null;
		try {
			conn = DBUtils.getConnection();
	
			stmt_get_course = conn.prepareStatement(SQLConstants.CHECK_COURSE_IN_CATALOG);
			stmt_get_course.setString(1, c.getCourseCode());
			ResultSet rs1 = stmt_get_course.executeQuery();
			if(rs1.next())
			{
				throw new CourseFoundException(c.getCourseCode());
			}
			stmt_get_course.close();
			
			stmt_get_professor = conn.prepareStatement(SQLConstants.GET_PROFESSOR);
			stmt_get_professor.setString(1, c.getInstructor());
			ResultSet rs2 = stmt_get_professor.executeQuery();
			
			if(!(rs2.next()))
			{
				throw new ProfessorNotFoundException(c.getInstructor());
			}		
			stmt_get_professor.close();
			stmt = conn.prepareStatement(SQLConstants.INSERT_COURSEINCATALOG);
			stmt.setString(1, c.getCourseCode());
			stmt.setString(2, c.getName());
			stmt.setInt(3, c.getNumStudents());
			stmt.setString(4, c.getInstructor());
			stmt.setDouble(5, c.getFee());
			stmt.setInt(6, c.getSemester());
			stmt.executeUpdate();
			stmt.close();

			stmt_professor = conn.prepareStatement(SQLConstants.UPDATE_COURSEOF_PROFESSOR);
			stmt_professor.setString(1, c.getCourseCode());
			stmt_professor.setString(2, c.getInstructor());
			stmt_professor.executeUpdate();
			stmt_professor.close();
			
		} catch (SQLException se) {
			se.printStackTrace();
			throw new AddCourseException(c.getCourseCode());
		}
		catch(ProfessorNotFoundException e)
		{
			throw new ProfessorNotFoundException(c.getInstructor());
		}
		catch(CourseFoundException e)
		{
			throw new CourseFoundException(c.getCourseCode());
		}
	}
	/**
	* Method to remove course using SQL commands
	* @param coursecode
	* @throws CourseNotFoundException
	* @throws CourseNotDeletedException
	*/
	
	public void removeCourse(String coursecode) throws CourseNotFoundException, CourseNotDeletedException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.REMOVE_COURSE_FROM_CATALOG);
			stmt.setString(1, coursecode);
			int row = stmt.executeUpdate();
			stmt.close();

			if (row == 0) {
				throw new CourseNotFoundException(coursecode);
			}
		} catch (SQLException se) {

			throw new CourseNotDeletedException(coursecode);
		}
	}
	/**
	* Method to assign professor to course
	* @param professorid
	* @param coursecode
	* @throws CourseNotAssignedToProfessorException
	*/
	
	public void assignProfessorToCourse(String professorid, String courseCode) throws CourseNotAssignedToProfessorException {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_course = null;
		try {
			conn = DBUtils.getConnection();		
			
			stmt = conn.prepareStatement(SQLConstants.UPDATE_COURSEOF_PROFESSOR);
			stmt.setString(1, courseCode);
			stmt.setString(2, professorid);
			int rowAffected_p = stmt.executeUpdate();
			stmt.close();
			if(rowAffected_p == 0)
			{
				throw new CourseNotAssignedToProfessorException(courseCode, professorid);
			}			
			
			stmt_course = conn.prepareStatement(SQLConstants.UPDATE_PROFESSORIN_CATALOG);
			stmt_course.setString(1, professorid);
			stmt_course.setString(2, courseCode);
			int rowAffected_c = stmt_course.executeUpdate();
			stmt_course.close();
			if(rowAffected_c == 0)
			{
				throw new CourseNotAssignedToProfessorException(courseCode, professorid);
			}

		} catch (SQLException se) {
			throw new CourseNotAssignedToProfessorException(courseCode, professorid);
		}
	}
	/**
	* Method to view course details
	* @param coursecode
	* @throws CourseNotFoundException
	* @return c
	*/

	public Course viewCourseDetails(String courseCode) throws CourseNotFoundException {
		Course c = new Course();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();

			stmt = conn.prepareStatement(SQLConstants.SELECT_CATALOG_WITH_COURSECODE);
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) 
				throw new CourseNotFoundException(courseCode);
//			} else {
				while (rs.next()) {
					c.setCourseCode(rs.getString("coursecode"));
					c.setName(rs.getString("coursename"));
					c.setNumStudents(rs.getInt("numstudents"));
					c.setInstructor(rs.getString("instructor"));
					c.setFee(rs.getDouble("coursefee"));
					c.setSemester(rs.getInt("semester"));
				}
//			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			throw new CourseNotFoundException(courseCode);
		}
		return c;
	}
	/**
	* Method to view listed courses
	* @return list of available courses
	*/
	public List<Course> viewCourses() {
		List<Course> cs = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();

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
			;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cs;
	}
	/**
	* Method to view list of unapproved students
	* @return List of unapproved students
	*/

	public List<Student> listOfUnapprovedStudents() {
		List<Student> uas = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
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
			;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uas;
	}
	/**
	 * Approve all Students using SQL commands
	 * 
	 */

	public void approveAllStudents() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.SET_ALLAPPROVED);
			stmt.setInt(1, 1);
			stmt.executeUpdate();
			stmt.close();
			;
		} catch (

		SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	* Method to approve student via student id
	* @param studentid
	* @throws StudentNotFoundForVerificationException
	*/
	
	public void approveStudentById(String studentid) throws StudentNotFoundForVerificationException {
		System.out.println(studentid);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.SET_APPROVEDBYID);
			stmt.setInt(1, 1);
			stmt.setString(2, studentid);
			int row = stmt.executeUpdate();
			stmt.close();

			if (row == 0) {
				throw new StudentNotFoundForVerificationException(studentid);
			}
		} catch (SQLException se) {
			throw new StudentNotFoundForVerificationException(studentid);
		}
	}
	/**
	* Method to view listed admins
	* @return list of admins
	*/

	public List<Admin> viewAdmins() {
		List<Admin> la = new ArrayList<Admin>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
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
			;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return la;
	}
	/**
	* Method to view listed professors
	* @return list of professors
	*/

	public List<Professor> viewProfessors() {

		List<Professor> lp = new ArrayList<Professor>();

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
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
			;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lp;
	}
	/**
	* Calculates the CPI (Cumulative Performance Index) of a student.
	* @param studentid the ID of the student whose CPI is to be calculated
	* @return the CPI of the student
	* @throws StudentNotRegisteredException if the student is not registered in the system
	*/

	public double calculateCpi(String studentid) throws StudentNotRegisteredException {

		String grade;
		double cgpa = 0.0, cpi = 0.0;
		int n = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.SELECT_GRADES);
			stmt.setString(1, studentid);

			ResultSet rs = stmt.executeQuery();
			if(!rs.next())
			{
				throw new StudentNotRegisteredException(studentid);
			}

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
			;

		} catch (SQLException se) {
			throw new StudentNotRegisteredException(studentid);
		}
		catch (StudentNotRegisteredException e) {
			throw new StudentNotRegisteredException(studentid);
		}
		return cpi;
	}

	@Override
	public void generateReportCard(String studentId) throws StudentNotRegisteredException {
		// TODO Auto-generated method stub

	}
}
