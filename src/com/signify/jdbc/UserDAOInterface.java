package com.signify.jdbc;

public interface UserDAOInterface {
	public int[] login(String username, String password);
    public boolean updatePassword(String username,String oldPassword,String newPassword);
}
