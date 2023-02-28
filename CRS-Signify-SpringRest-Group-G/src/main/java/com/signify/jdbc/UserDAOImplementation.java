/**
 * 
 */
package com.signify.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.signify.bean.User;
import com.signify.constants.SQLConstants;
import com.signify.exception.UserNotFoundException;
import com.signify.utils.DBUtils;

@Repository
public class UserDAOImplementation implements UserDAOInterface {

	/**
	 * Method to verify credentials and approve login
	 */
	public User login(String username, String password) throws UserNotFoundException {
		String userid = "";
		int roleid = -1;
		Connection conn = null;
		PreparedStatement stmt = null;
		User usr = new User();

		try {

			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.LOGIN_USER);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == true) {

				do {
					userid = rs.getString("userid");
					roleid = rs.getInt("roleid");
				} while (rs.next());
			} else {
				throw new UserNotFoundException(username);
			}

			usr.setUserId(userid);
			usr.setRoleid(roleid);
			rs.close();
			stmt.close();

		}

		catch (SQLException se) {
			se.printStackTrace();
		}
		return usr;
	}

	/**
	 * Method to update password in the database
	 */
	public boolean updatePassword(String username, String oldPassword, String newPassword)
			throws UserNotFoundException {
		Connection conn = null;
		PreparedStatement stmtSelect = null;
		PreparedStatement stmtUpdate = null;
		ResultSet rs = null;
		boolean success = false;

		try {
			conn = DBUtils.getConnection();
			stmtSelect = conn.prepareStatement(SQLConstants.VIEW_USER);
			stmtSelect.setString(1, username);
			stmtSelect.setString(2, oldPassword);
			rs = stmtSelect.executeQuery();
			if (rs == null) {
				throw new UserNotFoundException(username);
			}

			rs.next();
			int count = rs.getInt(1);
			if (count == 1) {
				stmtUpdate = conn.prepareStatement(SQLConstants.UPDATE_PASSWORD);
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
				if (rs != null)
					rs.close();
				if (stmtSelect != null)
					stmtSelect.close();
				if (stmtUpdate != null)
					stmtUpdate.close();
				if (conn != null)
					;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return success;
	}

}
