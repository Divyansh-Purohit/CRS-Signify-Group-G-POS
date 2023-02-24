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
	
	public SemesterNotRegisteredException(String sId)
	{
		this.studentId = sId;
	}
	
	public String getMessage()
	{
		return "\nSTUDENT \""+studentId+"\" NOT REGISTERED FOR ANY SEMESTER!\n";
	}
	
	

}
