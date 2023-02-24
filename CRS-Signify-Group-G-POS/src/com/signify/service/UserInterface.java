package com.signify.service;

public interface UserInterface {
	/**
	 * Method for user login
	 * @param username
	 * @param password
	 * @return
	 */
	public String[] login(String username, String password);
	/**
	 * Method to logout user
	 */
	public void logout();
	/**
	 * Method to update Details
	 */
	public void updateDetails();	
	/**
	 * Method to update password
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public boolean updatePassword(String username, String oldPassword, String newPassword);
	
}
