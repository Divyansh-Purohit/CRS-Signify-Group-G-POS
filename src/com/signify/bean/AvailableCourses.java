/**
 * 
 */
package com.signify.bean;

/**
 * @author dp201
 *
 */
public class AvailableCourses {
	private String courseCode, courseName;
	private int instructor, seats;
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getInstructor() {
		return instructor;
	}
	public void setInstructor(int instructor) {
		this.instructor = instructor;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
}
