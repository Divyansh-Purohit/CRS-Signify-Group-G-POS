/**
 * 
 */
package com.signify.service;

/**
 * @author dp201
 *
 */
public interface StudentInterface {
	public void viewGrades(int studentId);
	
	public void viewGrade(int studentId, String courseId);
	
	public void register();
		
	public void viewRegisterCourses(int studentId);
	
	public void addCourse(int studentId);
	
	public void dropCourse(int studentId, String courseId);
	
	public void payFees(int studentId);
	
}
