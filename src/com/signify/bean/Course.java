package com.signify.bean;
import java.util.*;
public class Course {
	private String courseCode, name;
	private int instructor;
	private boolean isOffered;
	private int seatsAvailable;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	private int type;
	
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	private HashMap<Integer, String> enrolledStudents = new HashMap<Integer, String>();
	
	public int getNumStudents() {
		return numStudents;
	}
	public HashMap<Integer, String> getEnrolledStudents() {
		return enrolledStudents;
	}
	public void setEnrolledStudents(HashMap<Integer, String> eS) {
		this.enrolledStudents = eS;
	}
	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}
	private int numStudents;
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInstructor() {
		return instructor;
	}
	public void setInstructor(int instructorId) {
		this.instructor = instructorId;
	}
	public boolean isOffered() {
		return isOffered;
	}
	public void setOffered(boolean isOffered) {
		this.isOffered = isOffered;
	}
	
	
}
