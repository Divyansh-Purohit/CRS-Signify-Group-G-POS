/**
 * 
 */
package com.signify.exception;

/**
 * Exception thrown when student is not found for approval
* @author
 *
 */
public class StudentNotFoundForApprovalException extends Exception {
	private int studentId;
	/**
	 * Constructor
	 * @param studentId
	 */
	public StudentNotFoundForApprovalException(int studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * Getter function for studentId
	 * @return studentId
	 */
	public int getStudentId() {
		return this.studentId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "studentId: " + studentId + " not found for approval!" ;
	}
}
