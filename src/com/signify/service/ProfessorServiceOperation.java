/**
 * 
 */
package com.signify.service;
import com.signify.bean.*;
import com.signify.jdbc.UserData;

import java.util.*;
/**
 * @author dp201
 *
 */
public class ProfessorServiceOperation extends UserServiceOperation implements ProfessorInterface{
	 
	public void viewEnrolledStudents(int professorId)
	{
		String c_id = UserData.professors.get(professorId).getCourseTaught();
		Course currCourse = UserData.courses.get(c_id);
		
		HashMap<Integer, String> hmp = currCourse.getEnrolledStudents();
		if(hmp.size() == 0)
		{
			System.out.println("\nNo students enrolled in your course!\n");
			return;
		}		
		System.out.println("\nList of Enrolled Students\n");	
		System.out.println("=========================");
		System.out.println("Student Id\tStudent Name\n");
	
		for(Map.Entry<Integer, String> m: hmp.entrySet()) {
			System.out.println(m.getKey()+"\t"+m.getValue());
		}
	}
	
	public void addGrades(int professorId, int StudentId)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Grade: ");
		sc.nextLine();
		String grade = sc.nextLine();
		Student currStudent = UserData.students.get(StudentId);
		HashMap<String, RegisteredCourse> hmp = currStudent.getRegCourses();
		Professor currProfessor = UserData.professors.get(professorId);
		String courseId = currProfessor.getCourseTaught();
		RegisteredCourse rc = hmp.get(courseId);
		rc.setGrade(grade);
		hmp.put(courseId, rc);
		return;
	}	
}
