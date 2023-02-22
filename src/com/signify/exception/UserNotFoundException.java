package com.signify.exception;

public class UserNotFoundException extends Exception {
	private String userId;

	
	public UserNotFoundException(String userId) {
		super();
		this.userId = userId;
	}
	public String getMessage() {
		return "User with userId: " + userId + " does not exist";
	}

}
