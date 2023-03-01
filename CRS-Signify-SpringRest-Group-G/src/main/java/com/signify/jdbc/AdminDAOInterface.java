package com.signify.jdbc;

import java.util.List;
import com.signify.exception.*;
import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;

/**
 * @author dp201
 *
 */

public interface AdminDAOInterface {
	/**
	 * Method to approve all students
	 * 
	 */
	public void approveAllStudents();

	/**
	 * Method to approve students by student id
	 * 
	 * @param studentid
	 * @throws StudentNotFoundForVerificationException
	 */

	public void approveStudentById(String studentid) throws StudentNotFoundForVerificationException;

	/**
	 * Method to view list of unapproved students
	 * 
	 * @return list of Unapproved students
	 */
	public List<Student> listOfUnapprovedStudents();

	/**
	 * Method to add a new admin
	 * 
	 * @param a
	 * @throws UserAlreadyExistException
	 */

	public void addAdmin(Admin a) throws UserAlreadyExistException;

	/**
	 * Method to view list of admins
	 * 
	 * @return list of admins
	 */

	public List<Admin> viewAdmins();

	/**
	 * Method to view list of professors
	 * 
	 * @return list of professors
	 */

	public List<Professor> viewProfessors();

	/**
	 * Method to add a new professor
	 * 
	 * @param p
	 * @throws ProfessorNotAddedException
	 * @throws UserAlreadyExistException
	 */

	public void addProfessor(Professor p) throws ProfessorNotAddedException, UserAlreadyExistException;

	/**
	 * Method to assign professor to a course
	 * 
	 * @param professorid
	 * @param courseCode
	 * @throws CourseNotAssignedToProfessorException
	 */
	public void assignProfessorToCourse(String professorid, String courseCode)
			throws CourseNotAssignedToProfessorException;

	/**
	 * Method to add a course
	 * 
	 * @param c
	 * @throws AddCourseException
	 * @throws ProfessorNotFoundException
	 * @throws CourseFoundException
	 */
	public void addCourse(Course c) throws AddCourseException, ProfessorNotFoundException, CourseFoundException;

	/**
	 * Method to remove a course
	 * 
	 * @param courseCode
	 * @throws CourseNotFoundException
	 * @throws CourseNotDeletedException
	 */
	public void removeCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException;

	/**
	 * Method to view course details
	 * 
	 * @param coursecode
	 * @return
	 * @throws CourseNotFoundException
	 */
	public Course viewCourseDetails(String coursecode) throws CourseNotFoundException;

	/**
	 * Method to calculate cpi of a student
	 * 
	 * @param studentid
	 * @return
	 * @throws StudentNotRegisteredException
	 */
	public double calculateCpi(String studentid) throws StudentNotRegisteredException;

	/**
	 * Method to generate report card of a student
	 * 
	 * @param studentId
	 * @throws StudentNotRegisteredException
	 */
	public void generateReportCard(String studentId) throws StudentNotRegisteredException;

}
