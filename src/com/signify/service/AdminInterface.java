package com.signify.service;

public interface AdminInterface {
	public void approveStudent(int adminId);

	public void addProfessor(int adminId);
		
	public void generateReportCard(int adminId, int studentId);
//	public void generateReportCard(int adminId, int studentId, String courseCode, int semester);
	
	public void addAdmin(int adminId);
	
	public void assignProfessorToCourse(int adminId, int professorId, String courseCode);
	
	public void addCourse(int adminId);
		
	public void removeCourse(int adminId, String courseCode);
	
	public void viewCourseDetails(int adminId, String courseCode);
	
	public double calculateCpi(int adminId, int studentId);
	
	public void viewProfessors(int adminId);
	
	public void viewAdmins(int adminId);
	
}
