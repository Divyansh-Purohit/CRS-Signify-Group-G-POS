/**
 * 
 */
package com.signify.service;

/**
 * @author dp201
 *
 */
public class AdminServiceOperation extends UserServiceOperation implements AdminInterface{

	public void approveStudent(int studentId)
	{
		System.out.println("Student Approved!");
	}
	
	public void addProfessor(int professorId)
	{
		System.out.println("Professor Added!");
	}
	
	public void generateReportCard(int studentId, int courseId, int semester)
	{
		System.out.println("Report Card Generated!");
	}
	
	public void assignProfessorToCourse(int professorId, int courseId)
	{
		System.out.println("Professor Assigned to Course");
	}
	
	public void addCourse(int courseId)
	{
		System.out.println("Course Added!");
	}
	
	public void removeCourse(int id)
	{
		System.out.println("Course Removed!");
	}
	
	public void viewCourseDetails(int courseId)
	{
		System.out.println("Course Details Viewed!");
	}
	
	public double calculateCpi(int studentId)
	{
		return 0.0;
	}
}
