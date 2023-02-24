package com.signify.exception;

/**
 * Exception to check if the course is available for adding.
 */
public class AddCourseException extends Exception {
	private String courseId;

	/***
	 * Constructor
	 * 
	 * @param courseId
	 */
	public AddCourseException(String courseCode) {
		this.courseId = courseId;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "\nCOURSE WITH COURSE CODE \""+courseId+"\" COULD NOT BE ADDED!\n";
	}
}
