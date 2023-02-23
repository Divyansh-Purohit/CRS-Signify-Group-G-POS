package com.signify.client;
import com.signify.bean.Student;
import com.signify.jdbc.ProfessorDAOImplementation;
import com.signify.service.ProfessorInterface;
import com.signify.service.ProfessorServiceOperation;
import java.util.*;
/**
 * @author dp201
 *
 */
public class CRSProfessorApplicationMenu {

	public void displayProfessorMenu(String professorId)
	{
		System.out.println("\nWELCOME TO PROFESSOR MENU");
		System.out.println("=========================");
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("PRESS 1 FOR VIEWING ENROLLED STUDENTS\nPRESS 2 TO ADD GRADES\nPRESS 3 TO LOGOUT\n");
			int choice = sc.nextInt();
			ProfessorInterface ps = new ProfessorServiceOperation();
//			ProfessorDAOImplementation pdi = new ProfessorDAOImplementation();
			switch(choice)
			{
				case 1:
				{
					System.out.println("\nLIST OF ENROLLED STUDENTS\n");
					List<Student> students = ps.viewEnrolledStudents(professorId);
					System.out.println("STUDENT ID");
					for (Student i : students) {
						System.out.println(i.getStudentid());
					}
					System.out.println();
					break;
				}
				case 2:
				{
					sc.nextLine();
					System.out.print("Enter Student Id: ");
					String studentid = sc.nextLine();
					System.out.print("Enter Grade: ");
					String grade = sc.nextLine();
					System.out.println("NGG"+studentid+""+grade);
					System.out.println("\nSTUDENT HAS BEEN GRADED!\n");
					break;
				}
				case 3:
				{
					return;
				}
				default:
				{
					System.out.println("\nINVALID INPUT RECEIVED!\n");
				}
			}
		}
	}
}
