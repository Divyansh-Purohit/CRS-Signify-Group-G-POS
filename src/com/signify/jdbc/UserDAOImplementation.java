/**
 * 
 */
package com.signify.collection
package com.signify.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.signify.jdbc.UserDAOInterface;
import java.util.Scanner;

/**
 * @author srish
 *
 */

public class UserDAOImplementation implements UserDAOInterface{
	public int[] login(String username, String password)
	{
		int user_id = -1
		int role = -1;
		Connection conn = null;
		PreparedStatement stmt = null;
				
		try{
			   
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			   String sql="select userid, roleid from user1 where username="+"\""+username+"\""+" and password="+"\""+password+"\"";
			   conn = DriverManager.getConnection(helper.Ids.DB_URL,helper.Ids.USER,helper.Ids.PASS);
			   String sql="select userid, roleid from user where username="+"\""+username+"\""+" and password="+"\""+password+"\"";
			   stmt = conn.prepareStatement(sql);			   
			   
			   ResultSet rs = stmt.executeQuery(sql);
			   
			   while(rs.next())
			   {
			      user_id = rs.getInt("userid");
			      role = rs.getInt("roleid");
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
		int[] ans = {roleid,userid};
		return ans;
	}
	public boolean updatePassword(String username, String oldPassword, String newPassword) {
    Connection conn = null;
    PreparedStatement stmtSelect = null;
    PreparedStatement stmtUpdate = null;
    ResultSet rs = null;
    boolean success = false;
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(helper.Ids.DB_URL, helper.Ids.USER, helper.Ids.PASS);
        String sqlSelect = "SELECT COUNT(*) FROM user1 WHERE username=? AND password=?";
        stmtSelect = conn.prepareStatement(sqlSelect);
        stmtSelect.setString(1, username);
        stmtSelect.setString(2, oldPassword);
        rs = stmtSelect.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count == 1) {
            String sqlUpdate = "UPDATE user1 SET password=? WHERE username=?";
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
    
    return success;
}

}
