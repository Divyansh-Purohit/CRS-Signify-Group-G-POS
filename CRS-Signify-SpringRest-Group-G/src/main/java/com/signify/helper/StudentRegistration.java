package com.signify.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.signify.bean.User;
import com.signify.bean.Student;

/**
 * @author dp201
 *
 */
public class StudentRegistration {

	private String name, password, address, bg, fname, phnum, doj, userid, studentid;
	private int age;

	public Student registerStudent() {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Name: ");
		name = sc.nextLine();
		System.out.print("Enter Password: ");
		password = sc.nextLine();
		System.out.print("Enter Address: ");
		address = sc.nextLine();
		while (true) {
			System.out.print("Enter Age: ");
			try {
				age = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("\nINVALID VALUE FOR AGE RECEIVED!\n");
				sc.nextLine();
			}
		}
		sc.nextLine();
		System.out.print("Enter Branch: ");
		String branch = sc.nextLine();
		System.out.print("Enter Blood Group: ");
		bg = sc.nextLine();
		System.out.print("Enter Father's Name: ");
		fname = sc.nextLine();
		while (true) {
			System.out.print("Enter Phone Number: ");
			try {
				phnum = sc.nextLine();
				if (phnum.length() != 10)
					throw new InputMismatchException();
				double d = Double.parseDouble(phnum);
				break;
			} catch (NumberFormatException e) {
				System.out.println("\nINVALID NUMBER RECEIVED!\n");
			} catch (InputMismatchException e) {
				System.out.println("\nINVALID NUMBER RECEIVED!\n");
			}
		}
		doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		userid = UUID.randomUUID().toString();
		studentid = UUID.randomUUID().toString();

		User newUser = new User();
		newUser.setUserId(userid);
		newUser.setName(name);
		newUser.setPassword(password);
		newUser.setAddress(address);
		newUser.setDoj(doj);

		Student newStudent = new Student();
		newStudent.setUserId(userid);
		newStudent.setStudentId(studentid);
		newStudent.setName(name);
		newStudent.setPassword(password);
		newStudent.setAddress(address);
		newStudent.setBranch(branch);
		newStudent.setDoj(doj);
		newStudent.setAge(age);
		newStudent.setBloodgroup(bg);
		newStudent.setFname(fname);
		newStudent.setPhnum(phnum);
		newStudent.setApproved(0);
		return newStudent;
	}
}
