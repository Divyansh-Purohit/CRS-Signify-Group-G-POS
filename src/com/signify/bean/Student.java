package com.signify.bean;
import java.time.LocalDate;
import java.util.*;
public class Student extends User{
	private int studentId;
	private int semester;
	private LocalDate dateOfRegistration;
	private boolean isApproved;
	
	private HashMap<String, RegisteredCourse> regCourses = new HashMap<String, RegisteredCourse>();
	private int numRegCourses = 0;
	
	public HashMap<String, RegisteredCourse> getRegCourses() {
		return regCourses;
	}
	
	public void setRegCourses(HashMap<String, RegisteredCourse> mp)
	{
		this.regCourses = mp;
	}

	public int getNumRegCourses() {
		return numRegCourses;
	}
	public void setNumRegCourses(int numRegCourses) {
		this.numRegCourses = numRegCourses;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public LocalDate getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(LocalDate dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	
	
}
