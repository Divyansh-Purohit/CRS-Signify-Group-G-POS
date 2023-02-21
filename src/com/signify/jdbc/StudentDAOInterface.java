package com.signify.jdbc;

public interface StudentDAOInterface {
	public void register(String username, String password, String address);
	
	public void viewGrades(int studentId);	

	public void viewGrade(int studentId, String courseCode);
		
	public void viewRegisterCourses(int studentId);
	
	public void addCourse(int studentId, String courseCode);
	
	public void dropCourse(int studentId, String courseCode);
	
	public void payFees(int studentId);
}
