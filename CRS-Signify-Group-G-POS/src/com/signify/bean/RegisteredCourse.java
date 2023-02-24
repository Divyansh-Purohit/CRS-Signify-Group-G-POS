package com.signify.bean;
public class RegisteredCourse{
	
	int semester, type;
	private String courseCode, courseName;

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String name) {
		this.courseName = name;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
