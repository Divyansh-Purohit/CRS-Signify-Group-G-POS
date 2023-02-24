/**
 * 
 */
package com.signify.exception;

/**
 * @author dp201
 *
 */
public class InvalidNumber extends Exception{
private String phnum;
	
	/**
	 * Constructor
	 * @param courseCode
	 */
	public InvalidNumber(String phnum) {
		this.phnum = phnum;
	}
	
	/**
	 * Getter method
	 * @return course code
	 */
	public String getPhnum() {
		return phnum;
	}
	
	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "\nINVALID NUMBER RECEIVED!\n";
	}

}
