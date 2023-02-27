package com.signify.service;

import java.util.List;
import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.AddCourseException;
import com.signify.exception.CourseFoundException;
import com.signify.exception.CourseNotAssignedToProfessorException;
import com.signify.exception.CourseNotDeletedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.ProfessorNotAddedException;
import com.signify.exception.ProfessorNotFoundException;
import com.signify.exception.StudentNotFoundForVerificationException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.exception.UserAlreadyExistException;

public interface AdminInterface {
	/**
	 * Method to add course
	 * 
	 * @param c
	 * @throws CourseFoundException
	 * @throws ProfessorNotFoundException
	 * @throws AddCourseException
	 */
	public void addCourse(Course c) throws AddCourseException, ProfessorNotFoundException, CourseFoundException;

	/**
	 * Method to remove course
	 * 
	 * @param courseCode
	 * @throws CourseNotDeletedException
	 * @throws CourseNotFoundException
	 */
	public void removeCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException;

	/**
	 * Method to view course details
	 * 
	 * @param courseCode
	 * @return
	 * @throws CourseNotFoundException
	 */
	public Course viewCourseDetails(String courseCode) throws CourseNotFoundException;

	/**
	 * Method to add Professor
	 * 
	 * @param p
	 * @throws UserAlreadyExistException
	 * @throws ProfessorNotAddedException
	 */
	public void addProfessor(Professor p) throws ProfessorNotAddedException, UserAlreadyExistException;

	/**
	 * Method to assign professor to course
	 * 
	 * @param professorid
	 * @param courseCode
	 * @throws CourseNotAssignedToProfessorException
	 */
	public void assignProfessorToCourse(String professorid, String courseCode)
			throws CourseNotAssignedToProfessorException;

	/**
	 * Method to view Professor
	 * 
	 * @return
	 */
	public List<Professor> viewProfessors();

	/**
	 * Method to add admin
	 * 
	 * @param a
	 * @throws UserAlreadyExistException
	 */
	public void addAdmin(Admin a) throws UserAlreadyExistException;

	/**
	 * Method to view Admins
	 * 
	 * @return
	 */
	public List<Admin> viewAdmins();

	/**
	 * Method to view list of unapproved students
	 * 
	 * @return
	 */
	public List<Student> listOfUnapprovedStudents();

	/**
	 * Method to approve all students
	 */
	public void approveAllStudents();

	/**
	 * Method to approve student by id
	 * 
	 * @param studentid
	 * @throws StudentNotFoundForVerificationException
	 */
	public void approveStudentById(String studentid) throws StudentNotFoundForVerificationException;

	/**
	 * Method to cakculate CPI
	 * 
	 * @param studentId
	 * @return 
	 * @throws StudentNotRegisteredException 
	 */
	public double calculateCpi(String studentId) throws StudentNotRegisteredException;

	/**
	 * Method to generate report card
	 * 
	 * @param studentId
	 */
	public void generateReportCard(String studentId);

}
