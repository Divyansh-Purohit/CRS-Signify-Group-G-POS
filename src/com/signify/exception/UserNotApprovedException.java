package com.signify.exception;

public class UserNotApprovedException extends Exception {
	private String userId;

	public UserNotApprovedException(String userId) {
		super();
		this.userId = userId;
	}

	public String getMessage() {
		return "This User " + userId + " is not approved";
	}

}
