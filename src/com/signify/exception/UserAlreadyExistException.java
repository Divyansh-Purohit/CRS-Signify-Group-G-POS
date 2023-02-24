package com.signify.exception;
public class UserAlreadyExistException extends Exception {
	private String userId;

	public UserAlreadyExistException(String userId) {
		super();
		this.userId = userId;
	}


	public String getMessage() {
		return "\nUSERNAME  ALREADY EXISTS!\n";
	}
}

