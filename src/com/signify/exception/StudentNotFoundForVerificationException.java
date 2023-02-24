package com.signify.exception;

public class StudentNotFoundForVerificationException extends Exception {

	private String studentId;

	public StudentNotFoundForVerificationException(String studentId) {
		this.studentId = studentId;
	}
	public String getMessage() {
		return "\nSTUDENT ID \"" + studentId + "\" NOT FOUND IN DATABASE!\n";
	}
}
