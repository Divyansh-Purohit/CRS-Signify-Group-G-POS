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

public interface AdminDAOInterface{

	public void approveAllStudents();
	
	public void approveStudentById(String studentid) throws StudentNotFoundForVerificationException;
	
	public List<Student> listOfUnapprovedStudents();
		
	public void addAdmin(Admin a) throws UserAlreadyExistException;
	
	public List<Admin> viewAdmins();
	
	public List<Professor> viewProfessors();
	
	public void addProfessor(Professor p) throws ProfessorNotAddedException, UserAlreadyExistException;
	
	public void assignProfessorToCourse(String professorid, String courseCode) throws CourseNotAssignedToProfessorException;
	
	public void addCourse(Course c) throws AddCourseException, ProfessorNotFoundException, CourseFoundException;
	
	public void removeCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException;
	
	public Course viewCourseDetails(String coursecode) throws CourseNotFoundException;
	
	public double calculateCpi(String studentid) throws StudentNotRegisteredException;
	
	public void generateReportCard(String studentId) throws StudentNotRegisteredException;
	
}
