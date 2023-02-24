package com.signify.jdbc;
import com.signify.exception.UserNotFoundException;

public interface UserDAOInterface {
	/**
	 * Method to validate the credentials of a user
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	public String[] login(String username, String password) throws UserNotFoundException;
	/**
	 * Method to update password of a user
	 * 
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws UserNotFoundException
	 */
    public boolean updatePassword(String username,String oldPassword,String newPassword) throws UserNotFoundException;
}
