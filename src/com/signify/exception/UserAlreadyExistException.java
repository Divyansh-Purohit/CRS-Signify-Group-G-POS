package com.signify.exception;
public class UserAlreadyExistException extends Exception {
	private String userId;

	/**
	 * Constructor
	 * @param userId
	 */
	public UserAlreadyExistException(String userId) {
		super();
		this.userId = userId;
	}

/**
 * Message is displayed when exception is thrown
 */
	public String getMessage() {
		return "\nUSERNAME  ALREADY EXISTS!\n";
	}
}

