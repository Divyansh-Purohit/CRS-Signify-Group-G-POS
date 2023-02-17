/**
 * 
 */
package com.signify.service;

/**
 * @author dp201
 *
 */
public interface StudentInterface {
	public void viewGrades();
	
	public void viewGrade(int courseId);
	
	public void register();
	
	public void changePassword();
	
	public void viewRegisterCourses();
	
	public void addCourse();
	
	public void dropCourse(int courseId);
	
	public void payFees();
	
}
