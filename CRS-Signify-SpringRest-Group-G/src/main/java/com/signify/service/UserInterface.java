package com.signify.service;

import com.signify.bean.User;
import com.signify.exception.UserNotFoundException;

public interface UserInterface {
	/**
	 * Method for user login
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	public User login(String username, String password) throws UserNotFoundException;

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
	 * 
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public boolean updatePassword(String username, String oldPassword, String newPassword);

}
