package com.signify.exception;

/**
 * Exception to check if seats are available for course registration
* @author
 *
 */
public class SeatNotAvailableException extends Exception{
	
	private String courseCode;

	/**
	 * Constructor
	 * @param courseCode
	 */
	public SeatNotAvailableException(String courseCode)
	{	
		this.courseCode = courseCode;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return  "Seats are not available in : " + courseCode;
	}


}
