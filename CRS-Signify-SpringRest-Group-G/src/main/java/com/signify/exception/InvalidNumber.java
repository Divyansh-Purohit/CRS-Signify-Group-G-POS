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
	 * @param phnum
	 */
	public InvalidNumber(String phnum) {
		this.phnum = phnum;
	}
	
	/**
	 * Getter method
	 * @return phnum
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
