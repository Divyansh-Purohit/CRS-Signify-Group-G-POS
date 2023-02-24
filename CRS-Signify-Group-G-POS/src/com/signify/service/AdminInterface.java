package com.signify.service;
import java.util.List;
import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;

public interface AdminInterface {
	/**
	 * Method to add course
	 * @param c
	 */
	public void addCourse(Course c);
	
	/**
	 * Method to remove course
	 * @param courseCode
	 */
	public void removeCourse(String courseCode);
	/**
	 * Method to view course details
	 * @param courseCode
	 * @return
	 */
	public Course viewCourseDetails(String courseCode);	
	/**
	 * Method to add Professor
	 * @param p
	 */
	public void addProfessor(Professor p);	
	/**
	 * Method to assign professor to course
	 * @param professorid
	 * @param courseCode
	 */
	public void assignProfessorToCourse(String professorid, String courseCode);	
	/**
	 * Method to view Professor
	 * @return
	 */
	public List<Professor> viewProfessors();	
	/**
	 * Method to add admin
	 * @param a
	 */
	public void addAdmin(Admin a);	
	/**
	 * Method to view Admins
	 * @return
	 */
	public List<Admin> viewAdmins();
	/**
	 * Method to view list of unapproved students
	 * @return
	 */
	public List<Student> listOfUnapprovedStudents();
	/**
	 * Method to approve all students
	 */
	public void approveAllStudents();
	/**
	 * Method to approve student by id
	 * @param studentid
	 */
	public void approveStudentById(String studentid);
	/**
	 * Method to cakculate CPI
	 * @param studentId
	 */
	public void calculateCpi(String studentId);
	/**
	 * Method to generate report card
	 * @param studentId
	 */
	public void generateReportCard(String studentId);
	
}
