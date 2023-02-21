package com.signify.service;

public interface AdminInterface {
	public void approveStudent();

	public double calculateCpi(int studentId);
		
	public void generateReportCard(int studentId);
//	public void generateReportCard(int adminId, int studentId, String courseCode, int semester);
		
	public void addCourse();
		
	public void removeCourse(String courseCode);
	
	public void viewCourseDetails(String courseCode);
	
	public void addProfessor();
	
	public void assignProfessorToCourse(int professorId, String courseCode);
	
	public void viewProfessors();
	
	public void addAdmin();
	
	public void viewAdmins();
	
}
