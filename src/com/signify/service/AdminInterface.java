package com.signify.service;
import java.util.List;
import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;

public interface AdminInterface {	
	public void addCourse(Course c);
	public void removeCourse(String courseCode);	
	public Course viewCourseDetails(String courseCode);	
	public void addProfessor(Professor p);	
	public void assignProfessorToCourse(String professorid, String courseCode);	
	public List<Professor> viewProfessors();	
	public void addAdmin(Admin a);	
	public List<Admin> viewAdmins();
	public List<Student> listOfUnapprovedStudents();
	public void approveAllStudents();
	public void approveStudentById(String studentid);
	public void calculateCpi(String studentId);
	public void generateReportCard(String studentId);
	
}
