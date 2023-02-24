/**
 * 
 */
package com.signify.exception;

/**
 * Exception to check if course is already present in catalog
 * 
 * @author
 *
 */
public class CourseFoundException extends Exception {
	private String courseCode;

	/***
	 * Constructor
	 * 
	 * @param courseCode
	 */
	public CourseFoundException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Getter method
	 * 
	 * @return course code
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "\nCOURSE WITH COURSE CODE \""+courseCode+"\" ALREADY EXISTS IN CATALOG!\n";
	}
}
