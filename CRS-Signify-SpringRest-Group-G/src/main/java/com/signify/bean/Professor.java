package com.signify.bean;

import java.io.Serializable;

public class Professor extends User implements Serializable {

	String department, designation;
	String courseTaught;

	public String getCourseTaught() {
		return courseTaught;
	}

	public void setCourseTaught(String courseTaught) {
		this.courseTaught = courseTaught;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
