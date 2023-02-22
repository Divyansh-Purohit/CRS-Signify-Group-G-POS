package com.signify.client;
import com.signify.jdbc.ProfessorDAOImplementation;
import com.signify.service.ProfessorInterface;
import com.signify.service.ProfessorServiceOperation;
import java.util.*;
/**
 * @author dp201
 *
 */
public class CRSProfessorApplicationMenu {

	public void displayProfessorMenu(int professorId)
	{
		System.out.println("\nWELCOME TO PROFESSOR MENU");
		System.out.println("=========================");
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("PRESS 1 FOR VIEWING ENROLLED STUDENTS\nPRESS 2 TO ADD GRADES\nPRESS 3 TO LOGOUT");
			int choice = sc.nextInt();
//			ProfessorInterface ps = new ProfessorServiceOperation();
			ProfessorDAOImplementation pdi = new ProfessorDAOImplementation();
			switch(choice)
			{
				case 1:
				{
					pdi.viewEnrolledStudents(professorId);
					break;
				}
				case 2:
				{
					System.out.print("Enter Student Id: ");
					int sId = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Grade: ");
					String grade = sc.nextLine();
					pdi.addGrades(professorId, sId, grade);
					break;
				}
				case 3:
				{
					return;
				}
				default:
				{
					System.out.println("\nInvalid Input Received!\n");
				}
			}
		}
	}
}
