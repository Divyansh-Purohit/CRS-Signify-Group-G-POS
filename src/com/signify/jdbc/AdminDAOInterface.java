package com.signify.jdbc;

import java.util.List;

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
	
	public void approveStudentById();
	
	public List<Student> listOfUnapprovedStudents();
		
	public void addAdmin(Admin a);
	
	public List<Admin> viewAdmins();
	
	public List<Professor> viewProfessors();
	
	public void addProfessor(Professor p);
	
	public void assignProfessorToCourse(String professorid, String courseCode);
	
	public void addCourse(Course c);
	
	public void removeCourse(String courseCode);
	
	public void viewCourseDetails(String coursecode);
	
	public void calculateCpi(String studentid);
	
	public void generateReportCard(String studentId);
	
}
