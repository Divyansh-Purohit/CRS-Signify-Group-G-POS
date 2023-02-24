/**
 * 
 */
package com.signify.exception;

/**
 * @author Rag_Patel
 *
 */
public class UserIdAlreadyInUseException extends Exception{
	private String userId;
	
	/**
	 * Constructor
	 * @param userId
	 */
	public UserIdAlreadyInUseException(String userId) {
		this.userId = userId;
	}
	/**
	 * get userid
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * Set professorId
	 * @param userId
	 */

	public void setProfessorId(String userId) {
		this.userId = userId;
	}

/**
 * Message is displayed when exception is thrown
 */
	
	@Override
	public String getMessage() {
		return "userId: " + userId + " is already in use.";
	}

}
