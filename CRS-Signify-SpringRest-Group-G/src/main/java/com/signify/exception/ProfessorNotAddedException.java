package com.signify.exception;

/**
 * Exception to check if the professor is not added successfully by admin
 * 
 * @author
 *
 */
public class ProfessorNotAddedException extends Exception {
	private String professorId;

	/**
	 * Constructor
	 * 
	 * @param professorId
	 */

	public ProfessorNotAddedException(String professorId) {
		this.professorId = professorId;
	}

	/**
	 * Getter function for professorId
	 * 
	 * @return
	 */
	public String getUserId() {
		return this.professorId;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "professorId: " + professorId + " not added!";
	}
}
