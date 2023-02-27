/**
 * 
 */
package com.signify.exception;

/**
 * @author dp201
 *
 */
public class SemesterNotRegisteredException extends Exception{
	private String studentId;
	/**
	 * Constructor
	 * @param sId
	 */
	
	public SemesterNotRegisteredException(String sId)
	{
		this.studentId = sId;
	}
	/**
	 * Message is thrown by exception
	 */
	public String getMessage()
	{
		return "\nSTUDENT \""+studentId+"\" NOT REGISTERED FOR ANY SEMESTER!\n";
	}
	
	

}
