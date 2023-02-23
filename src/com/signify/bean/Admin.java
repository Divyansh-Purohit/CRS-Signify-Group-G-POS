package com.signify.bean;
import java.time.LocalDate;
public class Admin extends User {
	private String adminName;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}	
}
