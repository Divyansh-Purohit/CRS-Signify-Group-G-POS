/**
 * 
 */
package com.signify.client;

import java.util.Scanner;

import com.signify.service.ProfessorInterface;
import com.signify.service.ProfessorServiceOperation;

/**
 * @author dp201
 *
 */
public class CRSProfessorApplicationMenu {

	public static void main(String[] args) {
	}
	public void displayProfessorMenu()
	{
	System.out.println("WELCOME TO PROFESSOR MENU");
	System.out.println("=========================");
	Scanner sc = new Scanner(System.in);
	System.out.println("PRESS 1 FOR VIEWING ENROLLED STUDENTS\nPRESS 2 TO ADD GRADES\nPRESS 3 TO GO BACK");
	int choice = sc.nextInt();
//	ProfessorServiceOperation ps = new ProfessorServiceOperation();
	ProfessorInterface ps = new ProfessorServiceOperation();
	while(choice <= 3)
	switch(choice)
	{
	case 1:
	{
		ps.viewEnrolledStudents();
		System.out.println("PRESS 1 FOR VIEWING ENROLLED STUDENTS\nPRESS 2 TO ADD GRADES\nPRESS 3 TO GO BACK");
		choice = sc.nextInt();
		break;
	}
	case 2:
	{
		ps.addGrades();
		System.out.println("PRESS 1 FOR VIEWING ENROLLED STUDENTS\nPRESS 2 TO ADD GRADES\nPRESS 3 TO GO BACK");
		choice = sc.nextInt();
		break;
	}
	case 3:
	{
		CRSApplicationMenu crss = new CRSApplicationMenu();
		crss.loginHandler();
		break;
	}
	}
	sc.close();
	}

}
