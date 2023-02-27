package com.signify.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Admin extends User implements Serializable {

	private String adminName;

	/**
	 * 
	 * Getter setter for admin name
	 */
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}
