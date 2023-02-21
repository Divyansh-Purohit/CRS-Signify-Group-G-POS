/**
 * 
 */
package com.signify.collection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.signify.jdbc.UserDAOInterface;

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
			   
			   Class.forName("com.mysql.jdbc.Driver");
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
}
