package com.signify.service;

public interface AdminInterface {
	public void approveStudent(int studentId);

	public void addProfessor(int professorId);
	
	public void generateReportCard(int studentId, int courseId, int semester);
	
	public void assignProfessorToCourse(int professorId, int courseId);
	
	public void addCourse(int courseId);
		
	public void removeCourse(int id);
	
	public void viewCourseDetails(int courseId);
	
	public double calculateCpi(int studentId);
	
}
