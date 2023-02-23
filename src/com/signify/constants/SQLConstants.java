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
	public static final String REGISTER_USER = "insert into user(userid, username, password, address, doj, roleid) values(?,?,?,?,?,?)";
	public static final String GET_USER_ID = "select userid from user where username=? and password=?";
	public static final String DELETE_USER_COURSE = "delete from registeredcourse where courseCode = ? and studentId = ?";
	public static final String UPADTE_CATALOG_AFTER_STUDENT = "update catalog SET numstudents=numstudents-1 WHERE coursecode=?";
	public static final String VIEW_GRADES = "select rc.courseCode as coursecode, cat.coursename as coursename, rc.grade from registeredcourse rc natural join catalog cat where rc.studentId = ? and type = 1";
	public static final String VIEW_REGISTERED_COURSES = "select rc.courseCode as coursecode, cat.coursename as coursename, rc.type as type from registeredcourse rc inner join catalog cat on cat.coursecode = rc.coursecode where rc.studentId=?";
	public static final String GET_FEES = "select catalog.coursecode as coursecode , coursename , coursefee from registeredcourse inner join catalog on registeredcourse.coursecode = catalog.coursecode where studentId=?";
	public static final String PAYMENT_CASH = "insert into payment(referenceid , studentid , mode, status , amount) values(? , ? , ? , ? , ?)";
	public static final String PAYMENT_CASH_OFFLINE = "insert into payment_offline(referenceid , cash, chequenum, bankname) values(? , ? , ? , ?)";
	public static final String PAYMENT_CHEQUE = "insert into payment(referenceid , studentid , mode, status, amount) values(? , ? , ? , ? , ?)";
	public static final String PAYMENT_CHEQUE_OFFLINE = "insert into payment_offline(referenceid , cash , chequenum, bankname) values(? , ? , ? , ?)";
	public static final String PAYMENT_NETBANK = "insert into payment(referenceid , studentid , mode, status, amount) values(? , ? , ? , ? , ?)";
	public static final String PAYMENT_NETBANK_ONLINE = "insert into payment_online(referenceid , card , cardnum , cardtype , modeoftransfer , accountnumber , ifscode) values(? , ? , ? , ? , ? , ? , ?)";
	public static final String PAYMENT_CARD = "insert into payment(referenceid , studentid , mode , status , amount) values(? , ? , ? , ? , ?)";
	public static final String PAYMENT_CARD_ONLINE = "insert into payment_online(referenceid, card, cardnum, cardtype, modeoftransfer, accountnumber, ifscode) values(? , ? , ? , ? , ? , ? , ?)";

	public static final String REGISTER_STUDENT = "insert into student(userid, studentid, studentname, branch, age, bloodgroup, fname, phnum, isapproved, numcourses, semester) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_STUDENT_ID = "select studentid from student where userid=?";
	public static final String GET_ISAPPROVED_STATUS = "select isapproved from student where studentid=?";
	public static final String GET_AVAILABLE_COURSES = "select coursecode, coursename, instructor, numstudents from catalog where numstudents < 10";
	public static final String ADD_COURSE = "insert into registeredcourse values(?,?,?,?,?)";
	public static final String DELETE_COURSE = "DELETE FROM registeredcourse where courseCode=? and studentId=?";
	public static final String UPDATE_CATALOG = "update catalog SET numstudents=numstudents-1 WHERE coursecode=?";
	public static final String VIEW_GRADE = "select rc.courseCode as coursecode, cat.instructor as instructor, cat.coursename as coursename, rc.grade from registeredcourse rc natural join catalog cat where rc.studentId= ? and type = 1 and cat.courseCode = ?";
	public static final String VIEW_COURSES = "select catalog.coursecode as coursecode , coursename , coursefee from registeredcourse inner join catalog on registeredcourse.coursecode = catalog.coursecode where studentId=?";

	// Admin Query Constants

	public static final String REGISTER_ADMIN = "insert into admin values(?,?)";
	public static final String REGISTER_PROFESSOR = "insert into professor values(?,?,?,?,?)";
	public static final String INSERT_COURSEINCATALOG = "insert into catalog (coursecode, coursename, numstudents, instructor, coursefee) values(?,?,?,?,?)";
	public static final String UPDATE_COURSEOF_PROFESSOR = "update professor set course = ? where professorid = ?";
	public static final String REMOVE_COURSE_FROM_CATALOG = "delete from catalog where coursecode=?";
	public static final String UPDATE_PROFESSORIN_CATALOG = "update catalog set instructor=? where coursecode=?";
	public static final String SELECT_CATALOG_WITH_COURSECODE = "select * from catalog where coursecode = ?";
	public static final String SELECT_CATALOG = "select * from catalog";
	public static final String SELECT_UNAPPROVED = "select u.userid as user_id, u.username as username from user u inner join Student s on u.userid = s.userid where s.isapproved=?";
	public static final String SET_ALLAPPROVED = "update student set isapproved=?";
	public static final String SET_APPROVEDBYID = "update student set isapproved=? where studentid=?";
	public static final String SELECT_ADMINS = "select * from admin";
	public static final String SELECT_PROFESSORS = "select * from professor";
	public static final String SELECT_GRADES = "select grade from registeredcourse where studentId=?";

	// professor query constants

	public static final String VIEW_ENROLLED = "select studentid from registeredcourse inner join professor on registeredcourse.coursecode = professor.course where professorid = ?";
	public static final String SELECT_COURSE = "select course from professor where professorid = ?";
	public static final String ADD_GRADES = "update registeredcourse set grade = ? where studentid = ? and coursecode = ?";

	// user query constants

	public static final String LOGIN_USER = "select userid, roleid from user where username=? and password=?";
	public static final String VIEW_USER = "SELECT COUNT(*) FROM user WHERE username=? AND password=?";
	public static final String UPDATE_PASSWORD = "UPDATE user SET password=? WHERE username=?";
}
// Admin Query Constants 
