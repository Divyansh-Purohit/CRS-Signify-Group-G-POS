/**
 * 
 */
package com.signify.exception;

/**
 * @author dp201
 *
 */
public class ProfessorNotTeachingException extends Exception{
	private String professorId;

	/***
	 * Constructor
	 * 
	 * @param professorId
	 */
	public ProfessorNotTeachingException(String professorId) {
		this.professorId = professorId;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "\nADMIN HAS NOT ASSIGNED YOU ANY COURSE YET!\n";
	}
}
