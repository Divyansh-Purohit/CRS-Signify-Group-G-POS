/**
 * 
 */
package com.signify.constants;

/**
 * @author dp201
 *
 */
public class SQLConstants {
	
	// Students Query Constants 
	public static final String REGISTER_USER= "insert into user(userid, username, password, address, doj, roleid) values(?,?,?,?,?,?)";
	public static final String GET_USER_ID="select userid from user where username=? and password=?";
	public static final String DELETE_USER_COURSE = "delete from registeredcourse where courseCode = ? and studentId = ?";
	public static final String UPADTE_CATALOG_AFTER_STUDENT = "update catalog SET numstudents=numstudents-1 WHERE coursecode=?";
	public static final String VIEW_GRADES= "select rc.courseCode as coursecode, cat.coursename as coursename, rc.grade from registeredcourse rc natural join catalog cat where rc.studentId = ? and type = 1";
	public static final String VIEW_REGISTERED_COURSES="select rc.courseCode as coursecode, cat.coursename as coursename, rc.type as type from registeredcourse rc inner join catalog cat on cat.coursecode = rc.coursecode where rc.studentId=?";
	public static final String GET_FEES = "select catalog.coursecode as coursecode , coursename , coursefee from registeredcourse inner join catalog on registeredcourse.coursecode = catalog.coursecode where studentId=?";
	public static final String PAYMENT_CASH="insert into payment(referenceid , studentid , mode, status , amount) values(? , ? , ? , ? , ?)";
	public static final String PAYMENT_CASH_OFFLINE="insert into payment_offline(referenceid , cash, chequenum, bankname) values(? , ? , ? , ?)";
	public static final String PAYMENT_CHEQUE="insert into payment(referenceid , studentid , mode, status, amount) values(? , ? , ? , ? , ?)";
	public static final String PAYMENT_CHEQUE_OFFLINE="insert into payment_offline(referenceid , cash , chequenum, bankname) values(? , ? , ? , ?)";
	public static final String PAYMENT_NETBANK="insert into payment(referenceid , studentid , mode, status, amount) values(? , ? , ? , ? , ?)";
	public static final String PAYMENT_NETBANK_ONLINE="insert into payment_online(referenceid , card , cardnum , cardtype , modeoftransfer , accountnumber , ifscode) values(? , ? , ? , ? , ? , ? , ?)";
	public static final String PAYMENT_CARD="insert into payment(referenceid , studentid , mode , status , amount) values(? , ? , ? , ? , ?)";
	public static final String PAYMENT_CARD_ONLINE="insert into payment_online(referenceid, card, cardnum, cardtype, modeoftransfer, accountnumber, ifscode) values(? , ? , ? , ? , ? , ? , ?)";


	// Admin Query Constants 
	

}