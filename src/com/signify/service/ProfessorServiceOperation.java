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
	 
	public void viewEnrolledStudents(int professorId)
	{
		ProfessorDAOImplementation pdi = new ProfessorDAOImplementation();
		List<Integer> es = pdi.viewEnrolledStudents(professorId);
		System.out.println("\nStudent ID");
		for(Integer x : es)
		{
			System.out.println(x);
		}
		System.out.println();
	}
	
	public void addGrades(int professorId, int StudentId, String grade)
	{
		ProfessorDAOImplementation pdi = new ProfessorDAOImplementation();
		pdi.addGrades(professorId, StudentId, grade);
	}	
}
