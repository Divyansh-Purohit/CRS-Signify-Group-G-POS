package com.signify.exception;

public class StudentNotFoundForVerificationException extends Exception {

	private String studentId;
/**
 * Constructor
 * @param studentId
 */
	public StudentNotFoundForVerificationException(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * Message is returned by exception
	 */
	public String getMessage() {
		return "\nSTUDENT ID \"" + studentId + "\" NOT FOUND IN DATABASE!\n";
	}
}
