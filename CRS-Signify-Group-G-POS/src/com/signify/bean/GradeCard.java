package com.signify.bean;
public class GradeCard extends Course{
	
//	attributes here!
	private int studentId;
	private int semester;
	private float cpi;
	//REGISTERED COURSES;
	
//	setters and getters here
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
	public float getCpi() {
		return cpi;
	}
	public void setCpi(float cpi) {
		this.cpi = cpi;
	}
	
}
