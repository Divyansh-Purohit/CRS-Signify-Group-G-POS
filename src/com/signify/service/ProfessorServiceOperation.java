/**
 * 
 */
package com.signify.service;
import com.signify.bean.*;
import com.signify.collection.UserData;
import com.signify.jdbc.ProfessorDAOImplementation;

import java.util.*;
/**
 * @author dp201
 *
 */
public class ProfessorServiceOperation extends UserServiceOperation implements ProfessorInterface{
	 
	public List<Student> viewEnrolledStudents(String professorId)
	{
		ProfessorDAOImplementation pdi = new ProfessorDAOImplementation();
		List<Student> es = pdi.viewEnrolledStudents(professorId);
		return es;
	}
	
	public void addGrades(String professorId, String StudentId, String grade)
	{
		ProfessorDAOImplementation pdi = new ProfessorDAOImplementation();
		pdi.addGrades(professorId, StudentId, grade);
	}	
}
