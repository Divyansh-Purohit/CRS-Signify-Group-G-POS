/**
 * 
 */
package com.signify.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author dp201
 *
 */
public class UserDAOImplementation implements UserDAOInterface{
	public String[] login(String username, String password)
	{
		int user_id = -1;
		String role = null;
		Connection conn = null;
		PreparedStatement stmt = null;
				
		try{
			   
			   conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			   String sql="select user_id, role from user where username="+"\""+username+"\""+" and password="+"\""+password+"\"";
			   stmt = conn.prepareStatement(sql);			   
			   
			   ResultSet rs = stmt.executeQuery(sql);
			   
			   while(rs.next())
			   {
			      user_id = rs.getInt("user_id");
			      role = rs.getString("role");
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
			 }try{
			    if(conn!=null)
			       conn.close();
			 	}catch(SQLException se){
			       se.printStackTrace();
			    }
		}
		String[] ans = {role, Integer.toString(user_id)};
		return ans;
	}
	
	public boolean updatePassword(String username, String oldPassword)
	{
		Connection conn = null;
	    PreparedStatement stmtSelect = null;
	    PreparedStatement stmtUpdate = null;
	    ResultSet rs = null;
	    boolean success = false;
	    
	    try {
	        conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
	        String sqlSelect = "SELECT COUNT(*) FROM user WHERE username=? AND password=?";
	        stmtSelect = conn.prepareStatement(sqlSelect);
	        stmtSelect.setString(1, username);
	        stmtSelect.setString(2, oldPassword);
	        rs = stmtSelect.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        if(count == 0)
	        {
	        	System.out.println("\nInvalid username or password!\n");
	        	return false;
	        }
	        if (count == 1) {
	        	Scanner sc = new Scanner(System.in);
	        	System.out.print("Enter New Password: ");
	        	String newPassword = sc.nextLine();
	            String sqlUpdate = "UPDATE user SET password=? WHERE username=?";
	            stmtUpdate = conn.prepareStatement(sqlUpdate);
	            stmtUpdate.setString(1, newPassword);
	            stmtUpdate.setString(2, username);
	            int rowsAffected = stmtUpdate.executeUpdate();
	            if (rowsAffected == 1) {
	                success = true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmtSelect != null) stmtSelect.close();
	            if (stmtUpdate != null) stmtUpdate.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    System.out.println("\nYour password has been updated!\n");
	    return success;
	}
}
