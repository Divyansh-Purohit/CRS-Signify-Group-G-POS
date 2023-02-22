package com.signify.exception;

public class ProfessorNotDeletedException extends Exception {

	/*
	 * Course Code which cannot be Deleted.
	 */
	private String professorId;

	public ProfessorNotDeletedException(String professorId) {
		this.professorId = professorId;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "Professor with professorId: " + professorId + " can not be deleted.";
	}
}