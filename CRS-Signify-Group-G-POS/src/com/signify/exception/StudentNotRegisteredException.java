package com.signify.exception;

/**
 * Exception to check if student is not registered 
* @author
 *
 */
public class StudentNotRegisteredException extends Exception{
	 private String studentId;
	 /**
	  * Constructor
	  * @param studentId
	  */
	 public StudentNotRegisteredException(String studentId)
	 {
		 this.studentId=studentId;
	 }
	 
	 /**
	  * getter function for studentName
	  * @return studentId
	  */
	 public String getStudentId()
	 {
		 return studentId;
	 }
	 /**
	  * Message is displayed when exception is thrown
	  */
	 
	 public String getMessage()
	 {
		 return "\nSTUDENT HAVING STUDENT ID \""+studentId+"\" IS NOT REGISTERED FOR YOUR COURSE!\n";
	 }
	 
}
