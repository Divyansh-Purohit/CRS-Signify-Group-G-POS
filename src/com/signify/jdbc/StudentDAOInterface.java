package com.signify.jdbc;

public interface StudentDAOInterface {
	public void register(String username, String password, String address);
	
	public void viewGrades(int studentId);	

	public void viewGrade(int studentId, String courseId);
		
	public void viewRegisterCourses(int studentId);
	
	public void addCourse(int studentId);
	
	public void dropCourse(int studentId, String courseId);
	
	public void payFees(int studentId);
}
