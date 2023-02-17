/**
 * 
 */
package com.signify.service;

/**
 * @author dp201
 *
 */
public class StudentServiceOperation extends UserServiceOperation implements StudentInterface{
	public void viewGrades()
	{
		System.out.println("Grades Viewed!");
	}
	
	public void viewGrade(int courseId)
	{
		System.out.println("Course Grade Viewed!");
	}
	
	public void register()
	{		
		System.out.println("Registered Successfully!");
	}
	
	public void changePassword()
	{	
		System.out.println("Password Changed!");
	}
	
	public void viewRegisterCourses()
	{	
		System.out.println("List of Registered Courses!");
	}
	
	public void addCourse()
	{	
		System.out.println("Enrolled in Course!");
	}
	
	public void dropCourse(int courseId)
	{
		System.out.println("Course Dropped!");
	}
	
	public void payFees()
	{		
		System.out.println("Fees Paid!");
	}
	
}
