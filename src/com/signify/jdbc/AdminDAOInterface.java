package com.signify.jdbc;
/**
 * @author dp201
 *
 */

public interface AdminDAOInterface{

	public void approveStudent();
		
	public void addAdmin();
	
	public void viewAdmins();
	
	public void viewProfessors();
	
	public void addProfessor();
	
	public void assignProfessorToCourse(int professor_id, String courseCode);
	
	public void addCourse();
	
	public void removeCourse(String courseCode);
	
	public void viewCourseDetails(String coursecode);
	
	public void calculateCpi(int studentid);
	
	public void generateReportCard(int studentId);
	
}
