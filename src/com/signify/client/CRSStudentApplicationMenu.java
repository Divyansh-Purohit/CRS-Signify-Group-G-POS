package com.signify.client;
import com.signify.bean.*;
import com.signify.service.StudentInterface;
import java.util.*;
import com.signify.service.*;

/**
 * @author dp201
 *
 */
public class CRSStudentApplicationMenu {

	public void displayStudentMenu(String studentid)
	{
		System.out.println("\nWELCOME TO STUDENT MENU");
		System.out.println("=========================");
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("PRESS 1 FOR SEMESTER REGISTRATION\nPRESS 2 TO VIEW GRADES\nPRESS 3 TO VIEW REGISTERED COURSE\nPRESS 4 TO ADD COURSE\nPRESS 5 TO DROP A COURSE\nPRESS 6 TO PAY FEES\nPRESS 7 TO LOGOUT\n");
			StudentInterface ss = new StudentServiceOperation();
			int choice = sc.nextInt();
			switch(choice)
			{
				case 1:
				{
//					ss.semesterRegistration(studentid);
					break;
				}
				case 2:
				{
					ss.viewGrades(studentid);
					System.out.println();
					break;
				}
				case 3:
				{
					System.out.println("\nCourse Code\tCourse Name\tSemester\tType\n");
					List<RegisteredCourse> rc = ss.viewRegisterCourses(studentid);
					for(RegisteredCourse x: rc)
					{
						System.out.println(x.getCourseCode()+"\t"+x.getCourseName()+"\t"+x.getSemester());
					}
					System.out.println();
					break;
				}
				case 4:
				{
					List<Course> ac = ss.getAvailableCourses();
					System.out.println("\nList of Available Courses\n");
					System.out.println("Course_Code  Course_Name  Course_Instructor  Seats");
					System.out.println("=================================================");
					for (Course x : ac) {
						int seatsAvailable = 10 - x.getNumStudents();
						System.out.println(
								x.getCourseCode() + "\t" + x.getName() + "\t" + x.getInstructor() + "\t" + seatsAvailable);
					}
					System.out.println();
					sc.nextLine();
					System.out.print("Enter Course Code: ");
					String courseCode = sc.nextLine();
					System.out.print("Enter Type: ");
					int type = sc.nextInt();
					ss.addCourse(studentid, courseCode, type);
					break;
				}
				case 5:
				{
					System.out.println("\nCourse Code\tCourse Name\tSemester\n");
					List<RegisteredCourse> rc = ss.viewRegisterCourses(studentid);
					for(RegisteredCourse x: rc)
					{
						System.out.println(x.getCourseCode()+"\t"+x.getCourseName()+"\t"+x.getSemester());
					}
						
					System.out.print("\nEnter Course Id: ");
					sc.nextLine();
					String courseId = sc.nextLine();
					ss.dropCourse(studentid, courseId);
					break;
				}
				case 6:
				{
					String referencedId;
					List<Course> courses= ss.getFees(studentid);
					double totalFee = 0;
					for(Course x: courses)
					{
						totalFee += x.getFee();
					}
					
					System.out.println("\nTOTAL FEES TO BE PAID: "+totalFee+"\n");
					
					while(true)
					{
						System.out.println("\nPRESS 1 FOR ONLINE PAYMENT\nPRESS 2 FOR OFFLINE PAYMENT\nPRESS 3 IF SCHOLARSHIP RECEIVED\n");
						int m = sc.nextInt();
						switch(m)
						{
							case 1:
								while(true)
								{
									System.out.println("\nPRESS 1 FOR PAYMENT THROUGH CARD\nPRESS 2 FOR PAYMENT THROUGH NET BANKING\nPRESS 3 TO GO BACK\n");
									int on = sc.nextInt();
									
									switch(on)
									{
										case 1:
										{
											sc.nextLine();
											System.out.print("\nENTER CARD NUMBER: ");
											String cardNumber = sc.nextLine();
											System.out.print("ENTER CARD TYPE: ");
											String cardType = sc.nextLine();
											referencedId = UUID.randomUUID().toString();
											Payment p = new Payment();
											p.setStudentId(studentid);
											p.setStatus(0);
											p.setAmount(00);
											p.setMode("ONLINE");
											p.setReferencedId(referencedId);
																		
											OnlinePayment onp = new OnlinePayment();
											onp.setCard(1);
											onp.setCardNumber(cardNumber);
											onp.setCardType(cardType);
											onp.setAccountNumber("NA");
											onp.setIfscode("NA");
											onp.setModeOfTransfer("NA");
											onp.setReferencedId(referencedId);
											ss.payFeesByCard(onp, p);
											break;								
										}
										case 2:
										{
											sc.nextLine();
											System.out.print("\nENTER MODE OF TRANSFER: ");
											String mot = sc.nextLine();
											System.out.print("ENTER ACCOUNT NUMBER: ");
											String accnum = sc.nextLine();
											System.out.print("ENTER IFSCODE: ");
											String ifsc = sc.nextLine();
											referencedId = UUID.randomUUID().toString();
											Payment p = new Payment();
											p.setStudentId(studentid);
											p.setStatus(0);
											p.setAmount(00);
											p.setMode("ONLINE");
											p.setReferencedId(referencedId);
											
											OnlinePayment onp = new OnlinePayment();
											onp.setCard(0);
											onp.setCardNumber("NA");
											onp.setCardType("NA");
											onp.setAccountNumber(accnum);
											onp.setIfscode(ifsc);
											onp.setModeOfTransfer(mot);
											onp.setReferencedId(referencedId);
											ss.payFeesByNetBanking(onp, p);
											break;
										}
										case 3:
										{
											return;
										}
										default:
										{
											System.out.println("\nINVALID INPUT RECEIVED!\n");
											break;
										}
									}
								}
							case 2:
							{
								outer: while(true)
								{
									System.out.println("\nPRESS 1 FOR CASH PAYMENT\nPRESS 2 FOR CHEQUE PAYMENT\nPRESS 3 TO GO BACK\n");
									int of = sc.nextInt();
									switch(of)
									{
										case 1:
										{
											
											referencedId = UUID.randomUUID().toString();
											Payment p = new Payment();
											p.setStudentId(studentid);
											p.setStatus(0);
											p.setMode("OFFLINE");
											p.setAmount(00);
											p.setMode("ONLINE");
											p.setReferencedId(referencedId);
											p.setReferencedId(referencedId);
											
											OfflinePayment ofp = new OfflinePayment();
											ofp.setReferencedId(referencedId);
											ofp.setCash(1);
											ofp.setChequeNumber(-1);
											ofp.setBankName("NA");
											ss.payFeesByCash(ofp, p);
											break;						
										}
										case 2:
										{
											System.out.print("\nENTER CHEQUE NUMBER: ");
											int chequenum = sc.nextInt();
											sc.nextLine();
											System.out.print("ENTER BANK NAME: ");
											String bankname = sc.nextLine();

											referencedId = UUID.randomUUID().toString();
											Payment p = new Payment();
											p.setStudentId(studentid);
											p.setStatus(0);
											p.setMode("OFFLINE");
											p.setAmount(00);
											p.setMode("ONLINE");
											p.setReferencedId(referencedId);
											p.setReferencedId(referencedId);
											
											OfflinePayment ofp = new OfflinePayment();
											ofp.setReferencedId(referencedId);
											ofp.setCash(0);
											ofp.setChequeNumber(chequenum);
											ofp.setBankName(bankname);
											ss.payFeesByCheque(ofp, p);
											break;				
										}
										case 3:
										{
											break outer;
										}
										default:
										{
											System.out.println("\nINVALID INPUT RECEIVED!\n");
											break;
										}
									}
								}	
							}
						}
					}
				}
				case 7:
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
