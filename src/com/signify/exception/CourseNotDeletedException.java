/**
 * 
 */
package com.signify.exception;

/**
 * Exception course is deleted from catalog
* @author
 *
 */
public class CourseNotDeletedException extends Exception{
private String courseCode;
	
	public CourseNotDeletedException(String courseCode)
	{	
		this.courseCode = courseCode;
	}

	/**
	 * Getter function for course code
	 * @return
	 */
	public String getCourseCode()
	{
		return courseCode;
	}
	
	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() 
	{
		return "\nCOURSE WITH COURSE CODE \"" + courseCode + "\" CANNOT BE DELETED!\n";
	}
}
