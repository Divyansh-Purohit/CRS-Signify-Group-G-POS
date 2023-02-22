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
	
	public static final String REGISTER_USER= "insert into user(username, password, address, doj, roleid) values(?,?,?,?,?)";
	public static final String GET_USER_ID="select userid from user where username=? and password=?";
	
	
	// Admin Query Constants 
	

}