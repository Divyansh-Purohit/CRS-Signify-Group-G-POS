/**
 * 
 */
package com.signify.exception;

/**
 * Exception course is  not assigned to professor
* @author
 *
 */
public class CourseNotAssignedToProfessorException extends Exception{
	private String courseCode;
	private String professorId;
	
	public CourseNotAssignedToProfessorException(String courseCode, String professorId) {
		this.courseCode = courseCode;
		this.professorId = professorId;
	}
	
	/**
	 * Get courseCode
	 * @return
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * get Professor id
	 * @return
	 */
	public String getProfessorId() {
		return professorId;
	}

	/**
	 * set professor id
	 * @param professorId
	 */
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	/**
	 * set course code
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "\nCOURSE CODE \"" + courseCode + "\" OR PROFESSOR ID \"" + professorId + "\" DOES NOT EXISTS!\n";
	}
}
