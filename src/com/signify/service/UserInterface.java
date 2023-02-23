package com.signify.service;

public interface UserInterface {
	public String[] login(String username, String password);
	public void logout();
	public void updateDetails();	
	public boolean updatePassword(String username, String oldPassword, String newPassword);
	
}
