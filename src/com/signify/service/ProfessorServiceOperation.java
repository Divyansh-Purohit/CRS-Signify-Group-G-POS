/**
 * 
 */
package com.signify.service;
import com.signify.bean.*;
import com.signify.collection.UserData;
import com.signify.exception.ProfessorNotTeachingException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.jdbc.ProfessorDAOImplementation;

import java.util.*;
/**
 * @author dp201
 *
 */
public class ProfessorServiceOperation extends UserServiceOperation implements ProfessorInterface{
	 
	ProfessorDAOImplementation pdi = new ProfessorDAOImplementation();
	/**
	 * viewing enrolled students
	 */
	public List<Student> viewEnrolledStudents(String professorId)
	{
		
		List<Student> es = null;	
		try {
			es = pdi.viewEnrolledStudents(professorId);
		}
		catch(ProfessorNotTeachingException e)
		{
			System.out.println(e.getMessage());
		}
		return es;
	}
	
	/**
	 * adding grades
	 */
	
	public void addGrades(String professorId, String StudentId, String grade)
	{
		try {
			pdi.addGrades(professorId, StudentId, grade);
			System.out.println("\nSTUDENT HAS BEEN GRADED!\n");
		} catch (ProfessorNotTeachingException | StudentNotRegisteredException e) {
			System.out.println(e.getMessage());
		}
	}	
}
