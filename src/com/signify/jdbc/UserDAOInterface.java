package com.signify.jdbc;
import com.signify.exception.UserNotFoundException;

public interface UserDAOInterface {
	public String[] login(String username, String password) throws UserNotFoundException;
    public boolean updatePassword(String username,String oldPassword,String newPassword) throws UserNotFoundException;
}
