/**
 * 
 */
package com.signify.exception;

/**
 * Exception to check if the maximum number of registered courses is exceeded
* @author
 *
 */
public class CourseLimitExceedException extends Exception{
	
	private String studentId;

	/**
	 * 
	 * @param studentId
	 */
	public CourseLimitExceedException(String studentId)
	{	
		this.studentId = studentId;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() 
	{
		return "\nYOU CANNOT REGISTER FOR MORE COURSES OF THIS TYPE\n";
	}


}
