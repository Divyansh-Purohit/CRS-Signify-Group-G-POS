package com.signify.jdbc;

public interface UserDAOInterface {
	public String[] login(String username, String password);
    public boolean updatePassword(String username,String oldPassword,String newPassword);
}
