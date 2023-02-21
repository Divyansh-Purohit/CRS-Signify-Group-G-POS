/**
 * 
 */
package com.signify.jdbc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * @author dp201
 *
 */
public class AdminDAOImplementation {
	
	
	public void addAdmin(String username, String password, String address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_id = null;
		PreparedStatement stmt_admin = null;
		int id = -1;
		   
		   try{
			   	Class.forName("com.mysql.jdbc.Driver");
			   	conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			    String sql="insert into user (username, password, address, role, doj) values(?,?,?,?,?)";
			    String sql_id="select user_id from user where username="+"\""+username+"\""+" and password="+"\""+password+"\"";
			    String sql_admin="insert into admin values(?,?)";
			    stmt = conn.prepareStatement(sql);
			    stmt_id = conn.prepareStatement(sql_id);
			    stmt_admin = conn.prepareStatement(sql_admin);
			   
			    String doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			    
			    stmt.setString(1, username);
			    stmt.setString(2, password);
			    stmt.setString(3, address);
			    stmt.setString(4, "admin");
			    stmt.setDate(5, Date.valueOf(doj));			    
			    stmt.executeUpdate();
			    stmt.close();      
			   
			    ResultSet rs = stmt_id.executeQuery(sql_id);
			   
			    while(rs.next()){
			         id  = rs.getInt("user_id");
			    }	
			    
			    stmt_id.close();

			    stmt_admin.setInt(1, id);
			    stmt_admin.setString(2, username);
			    stmt_admin.executeUpdate();  
			    stmt_admin.close();
			     
			    conn.close();
		   }
		   catch(SQLException se){
			  se.printStackTrace();
		   }catch(Exception e){
			  e.printStackTrace();
		   }finally{
			  try{
			     if(stmt!=null)
			        stmt.close();
			  }catch(SQLException se2){
			  }
			  try{
			     if(conn!=null)
			        conn.close();
			  }catch(SQLException se){
			     se.printStackTrace();
			  }
			}
	}
	
	public void addProfessor(String username, String password, String address, String designation, String department)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_id = null;
		PreparedStatement stmt_professor = null;
		int id = -1;
		   
		   try{
			   	Class.forName("com.mysql.jdbc.Driver");
			   	conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			    String sql="insert into user (username, password, address, role, doj) values(?,?,?,?,?)";
			    String sql_id="select user_id from user where username="+"\""+username+"\""+" and password="+"\""+password+"\"";
			    String sql_professor="insert into professor values(?,?,?,?,?)";
			    stmt = conn.prepareStatement(sql);
			    stmt_id = conn.prepareStatement(sql_id);
			    stmt_professor = conn.prepareStatement(sql_professor);
			   
			    String doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			    
			    stmt.setString(1, username);
			    stmt.setString(2, password);
			    stmt.setString(3, address);
			    stmt.setString(4, "professor");
			    stmt.setDate(5, Date.valueOf(doj));			    
//			    stmt.setDate(5, Date.valueOf("2023-02-20"));
			    stmt.executeUpdate();
			    stmt.close();      
			   
			    ResultSet rs = stmt_id.executeQuery(sql_id);
			   
			    while(rs.next()){
			         id  = rs.getInt("user_id");
			    }			     
			    stmt_id.close();
			    
			    stmt_professor.setInt(1, id);
			    stmt_professor.setString(2, username);
			    stmt_professor.setString(3, designation);
			    stmt_professor.setString(4, department);
			    stmt_professor.setString(3, designation);
			    stmt_professor.setString(3, designation);
			    stmt_professor.executeUpdate();
			    stmt_professor.close();
			     
			    conn.close();
		   }
		   catch(SQLException se){
			  se.printStackTrace();
		   }catch(Exception e){
			  e.printStackTrace();
		   }finally{
			  try{
			     if(stmt!=null)
			        stmt.close();
			  }catch(SQLException se2){
			  }
			  try{
			     if(conn!=null)
			        conn.close();
			  }catch(SQLException se){
			     se.printStackTrace();
			  }
			}
	}
	
	public void addCourse(String courseCode, String courseName, int instructor)
	{
		Connection conn = null;
		PreparedStatement stmt = null;		   
		   try{
			   	Class.forName("com.mysql.jdbc.Driver");
			   	conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			    String sql="insert into catalog (coursecode, coursename, numstudents, instructor) values(?,?,?,?)";
			    stmt = conn.prepareStatement(sql);
			     
			    stmt.setString(1, courseCode);
			    stmt.setString(2, courseName);
			    stmt.setInt(3, 0);
			    stmt.setInt(4, instructor);
			    stmt.executeUpdate();
			    stmt.close();      
			        
			    conn.close();
		   }
		   catch(SQLException se){
			  se.printStackTrace();
		   }catch(Exception e){
			  e.printStackTrace();
		   }finally{
			  try{
			     if(stmt!=null)
			        stmt.close();
			  }catch(SQLException se2){
			  }
			  try{
			     if(conn!=null)
			        conn.close();
			  }catch(SQLException se){
			     se.printStackTrace();
			  }
		}
	}
	
	public void removeCourse(String coursecode)
	{
		Connection conn = null;
		PreparedStatement stmt = null;		   
		   try{
			   	Class.forName("com.mysql.jdbc.Driver");
			   	conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			    String sql="delete from catalog where coursecode="+"\""+coursecode+"\"";
			    stmt = conn.prepareStatement(sql);
			    stmt.executeUpdate();
			    stmt.close();           
			    conn.close();
		   }
		   catch(SQLException se){
			  se.printStackTrace();
		   }catch(Exception e){
			  e.printStackTrace();
		   }finally{
			  try{
			     if(stmt!=null)
			        stmt.close();
			  }catch(SQLException se2){
			  }
			  try{
			     if(conn!=null)
			        conn.close();
			  }catch(SQLException se){
			     se.printStackTrace();
			  }
		}
	}
	
	public void assignProfessorToCourse(int professor_id, String courseCode)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_course = null;
		   try{
			   	Class.forName("com.mysql.jdbc.Driver");
			   	conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			    String sql="insert into professor(coursetaught) values(?) where professor_id="+professor_id;
			    String sql_course="insert into catalog(instructor) values(?) where coursecode="+"\""+courseCode+"\"";
			    stmt = conn.prepareStatement(sql);
			    stmt_course = conn.prepareStatement(sql_course);
			    
			    stmt.setString(1, courseCode);
			    stmt.executeUpdate();
			    stmt.close(); 
			    
			    stmt_course.setInt(1, professor_id);
			    stmt_course.close();
			    
			    conn.close();
		   }
		   catch(SQLException se){
			  se.printStackTrace();
		   }catch(Exception e){
			  e.printStackTrace();
		   }finally{
			  try{
			     if(stmt!=null)
			        stmt.close();
			  }catch(SQLException se2){
			  }
			  try{
			     if(conn!=null)
			        conn.close();
			  }catch(SQLException se){
			     se.printStackTrace();
			  }
		}
	}
	
	public void viewCourseDetails(String courseCode)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		   try{
			   	Class.forName("com.mysql.jdbc.Driver");
			   	conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			    String sql="select * from catalog";
			    stmt = conn.prepareStatement(sql);
			    
			    ResultSet rs = stmt.executeQuery(sql);

			    while(rs.next()){

			         String cc  = rs.getString("coursecode");
			         String coursename = rs.getString("coursename");
			         int numstudents = rs.getInt("numstudents");
			         int instructor = rs.getInt("instructor");

			         System.out.println("Course Code: " + cc);
			         System.out.println("Course Name: " + coursename);
			         System.out.println("Number of Students Enrolled: " + numstudents);
			         System.out.println("Instructor Id: " + instructor);
			      }
			      rs.close();
			      stmt.close();
			      conn.close();
		   }
		   catch(SQLException se){
			  se.printStackTrace();
		   }catch(Exception e){
			  e.printStackTrace();
		   }finally{
			  try{
			     if(stmt!=null)
			        stmt.close();
			  }catch(SQLException se2){
			  }
			  try{
			     if(conn!=null)
			        conn.close();
			  }catch(SQLException se){
			     se.printStackTrace();
			  }
		}
	}
	
	public void approveStudent()
	{
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_1 = null;
		PreparedStatement stmt_2 = null;
		   try{
			   	Class.forName("com.mysql.jdbc.Driver");
			   	conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			    String sql="select u.user_id as user id, u.username as username from user u,student s where s.isapproved=no";
			    stmt = conn.prepareStatement(sql);
			    
			    System.out.println("\nUser ID\tUsername\n");
			    ResultSet rs = stmt.executeQuery(sql);
			    
			    
			    while(rs.next()){

			         int u_id  = rs.getInt("user_id");
			         String u_name = rs.getString("username");
			         
			         System.out.println(u_id+"\t"+u_name);
			    }
			    rs.close();
			    stmt.close();
			    
			    System.out.println("\nPRESS 1 TO APPROVE ALL STUDENTS\nPRESS 2 TO APPROVE STUDENT BY ID\nPRESS 3 TO GO BACK\n");
			    int choice = sc.nextInt();
			    switch(choice)
			    {
				    case 1:
				    {
				    	String sql_1="update student set isapproved=yes";
				    	stmt_1 = conn.prepareStatement(sql_1);
				    	stmt_1.executeUpdate();
				    	stmt_1.close();
				    }
				    case 2:
				    {
				    	System.out.print("\nEnter Student ID to Approve: ");
				    	int s_id = sc.nextInt();
				    	String sql_2="update student set isapproved=yes where student_id="+s_id;
				    	stmt_2 = conn.prepareStatement(sql_2);
				    	stmt_2.executeUpdate();
				    	stmt_2.close();
				    	
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
		
			    conn.close();
		   }
		   catch(SQLException se){
				  se.printStackTrace();
			   }catch(Exception e){
				  e.printStackTrace();
			   }finally{
				  try{
				     if(stmt!=null)
				        stmt.close();
				  }catch(SQLException se2){
				  }
				  try{
				     if(conn!=null)
				        conn.close();
				  }catch(SQLException se){
				     se.printStackTrace();
				  }
			  }
	}
	
	public void viewAdmins()
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		   try{
			   	Class.forName("com.mysql.jdbc.Driver");
			   	conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			    String sql="select * from admin";
			    stmt = conn.prepareStatement(sql);
			    
			    System.out.println("\nAdmin ID\tAdmin Username\n");
			    ResultSet rs = stmt.executeQuery(sql);
			    
			    while(rs.next()){

			         int u_id  = rs.getInt("admin_id");
			         String u_name = rs.getString("username");
			         
			         System.out.println(u_id+"\t"+u_name);
			    }
			    System.out.println();
			    
			    rs.close();
			    stmt.close();
			    conn.close();
		   }
		   catch(SQLException se){
				  se.printStackTrace();
			   }catch(Exception e){
				  e.printStackTrace();
			   }finally{
				  try{
				     if(stmt!=null)
				        stmt.close();
				  }catch(SQLException se2){
				  }
				  try{
				     if(conn!=null)
				        conn.close();
				  }catch(SQLException se){
				     se.printStackTrace();
				  }
		   }
	}
	
	public void viewProfessors()
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		   try{
			   	Class.forName("com.mysql.jdbc.Driver");
			   	conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			    String sql="select * from professor";
			    stmt = conn.prepareStatement(sql);
			    
			    System.out.println("\nProfessor ID\tProfessor Username\tDesignation\tDepartment\tCourse Taught\n");
			    ResultSet rs = stmt.executeQuery(sql);
			    
			    while(rs.next()){

			         int u_id  = rs.getInt("professor_id");
			         String u_name = rs.getString("username");
			         String designation = rs.getString("designation");
			         String department = rs.getString("department");
			         String courseCode = rs.getString("coursecode");
			         
			         System.out.println(u_id+"\t"+u_name+"\t"+designation+"\t"+department+"\t"+courseCode);
			    }
			    System.out.println();
			    rs.close();
			    stmt.close();
			    conn.close();
		   }
		   catch(SQLException se){
				  se.printStackTrace();
			   }catch(Exception e){
				  e.printStackTrace();
			   }finally{
				  try{
				     if(stmt!=null)
				        stmt.close();
				  }catch(SQLException se2){
				  }
				  try{
				     if(conn!=null)
				        conn.close();
				  }catch(SQLException se){
				     se.printStackTrace();
				  }
		   }
	}
}

