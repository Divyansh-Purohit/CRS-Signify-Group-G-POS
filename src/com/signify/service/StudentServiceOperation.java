package com.signify.service;

import com.signify.collection.UserData;
import com.signify.jdbc.StudentDAOImplementation;
import com.signify.bean.*;
import helper.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author dp201
 *
 */

public class StudentServiceOperation extends UserServiceOperation implements StudentInterface {

	StudentDAOImplementation sdi = new StudentDAOImplementation();

	public void register() {
		Scanner sc = new Scanner(System.in);
		String[] userDetails = UserAdditionHelper.detailsHelper();
		String username = userDetails[0];
		String address = userDetails[1];
		String password = userDetails[2];
		System.out.print("Enter Semester: ");
		int sem = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Branch: ");
		String branch = sc.nextLine();
		System.out.print("Enter Batch: ");
		String batch = sc.nextLine();
		System.out.print("Enter Blood Group: ");
		String bloodgroup = sc.nextLine();
		System.out.print("Enter Father's Name: ");
		String fname = sc.nextLine();
		System.out.print("Enter Phone Number: ");
		String phnum = sc.nextLine();
		String doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		sdi.register(username, password, address, sem, branch, batch, bloodgroup, fname, phnum, doj);
		System.out.println("\nStudent Registration Successful! Waiting for approval from admin.");
	}

	public void viewGrades(int studentId) {
		sdi.viewGrades(studentId);
	}

	public void viewGrade(int studentId, String courseId) {
		sdi.viewGrade(studentId, courseId);
	}

	public void viewRegisterCourses(int studentId) {
		List<Course> rcourses = sdi.viewRegisteredCourses(studentId);
		System.out.println("Course_Code  Course_Name  Course_Instructor  Type");
		System.out.println("=================================================");
		for (Course x : rcourses) {
			System.out.println(x.getCourseCode() + "\t" + x.getName() + "\t" + x.getInstructor() + "\t" + x.getType());
		}
	}

	public void addCourse(int studentId) {
		List<Course> ac = sdi.getAvailableCourses();
		System.out.println("Course_Code  Course_Name  Course_Instructor  Seats");
		System.out.println("=================================================");
		for (Course x : ac) {
			System.out.println(
					x.getCourseCode() + "\t" + x.getName() + "\t" + x.getInstructor() + "\t" + x.getSeatsAvailable());
		}
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course Code: ");
		String cc = sc.nextLine();
		System.out.println("Enter Type (1/2): ");
		int type = sc.nextInt();
		sdi.addCourse(studentId, cc, type);
	}

	public void dropCourse(int studentId, String courseId) {
		sdi.dropCourse(studentId, courseId);
	}

	public void payFees(int studentId) {
		int amount = sdi.getFees(studentId);
		Scanner sc = new Scanner(System.in);
		int choice;
		while (true) {
			System.out.println(
					"PRESS 1 FOR OFFLINE PAYMENT\nPRESS 2 FOR ONLINE PAYMENT\nPRESS 3 IF SCHOLARSHIP RECEIVED\nPRESS 4 TO GO BACK\n");
			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				int mode;
				while (true) {
					System.out.print("PRESS 1 FOR CASH PAYMENT\nPRESS 2 FOR CHEQUE PAYMENT\nPRESS 3 TO GO BACK\n");
					mode = sc.nextInt();
					switch (mode) {
					case 1: {
						sdi.payFeesByCash(studentId, amount);
						System.out.println("Deposit the Fee in Office");
						return;
					}
					case 2: {
						System.out.print("ENTER BANK NAME: ");
						sc.nextLine();
						String bankName = sc.nextLine();
						System.out.print("ENTER CHEQUE NUMBER: ");
						String chequeNumber = sc.next();
						sdi.payFeesByCheque(studentId, bankName, chequeNumber, amount);
						return;
					}
					case 3: {
						return;
					}
					default: {
						System.out.println("\nInvalid Input Received!\n");
					}
					}
				}
			}
			case 2: {
				int mode;
				while (true) {
					System.out.print(
							"PRESS 1 FOR CARD PAYMENT\nPRESS 2 FOR PAYMENT THROUGH NET BANKING\nPRESS 3 TO GO BACK\n");
					mode = sc.nextInt();
					switch (mode) {
					case 1: {
						System.out.print("ENTER CARD NUMBER: ");
						sc.nextLine();
						String cardNumber = sc.nextLine();
						System.out.print("ENTER CARD TYPE: ");
						String cardType = sc.nextLine();
						sdi.payFeesByCard(studentId, cardNumber, cardType, amount);
						return;
					}
					case 2: {
						System.out.print("ENTER MODE of TRANSFER: ");
						sc.nextLine();
						String modeOfTransfer = sc.nextLine();
						System.out.print("ENTER ACCOUNT NUMBER: ");
						String accountNumber = sc.nextLine();
						System.out.print("ENTER IFS CODE: ");
						String ifsCode = sc.nextLine();
						sdi.payFeesByNetBanking(studentId, modeOfTransfer, accountNumber, ifsCode, amount);
						return;
					}
					case 3: {
						return;
					}
					default: {
						System.out.println("\nInvalid Input Received!\n");
					}
					}
				}
			}
			case 3: {
				System.out.print("Enter Scholarship Name: ");
				sc.nextLine();
				String schname = sc.nextLine();
				return;
			}
			case 4: {
				return;
			}
			default: {
				System.out.println("\nInvalid Input Received!\n");
			}
			}
		}
	}
}
