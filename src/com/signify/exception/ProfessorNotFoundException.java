package com.signify.exception;


/**
 * Exception to check if professor is existing in database
* @author dp201
 *
 */
public class ProfessorNotFoundException extends Exception{
	private String professorId;
	
	public ProfessorNotFoundException(String professorId)
	{	
		this.professorId = professorId;
	}

	/**
	 * Getter function for professor id
	 * @return
	 */
	public String getProfessorId()
	{
		return professorId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() 
	{
		return "\nPROFESSOR WITH PROFESSOR ID \""+professorId+"\" NOT PRESENT IN DATABASE!\n";
	}
}
