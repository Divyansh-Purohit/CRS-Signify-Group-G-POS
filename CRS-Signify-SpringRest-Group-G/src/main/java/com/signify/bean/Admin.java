package com.signify.bean;

import java.io.Serializable;

/**
 * The Class Admin.
 *
 * @author Himanshu Yadav
 */
public class Admin extends User implements Serializable {

	/** The admin name. */
	private String adminName;

	/**
	 * Getter setter for admin name.
	 *
	 * @return the admin name
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * Sets the admin name.
	 *
	 * @param adminName the new admin name
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}
