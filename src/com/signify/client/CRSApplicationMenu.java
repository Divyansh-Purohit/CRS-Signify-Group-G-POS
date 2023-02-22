package com.signify.client;
import com.signify.service.*;
import com.signify.collection.*;
import com.signify.bean.*;

import java.time.LocalDate;
import java.util.*;
/**
 * @author dp201
 *
 */
public class CRSApplicationMenu {
		
	public static void main(String args[])
	{
		UserServiceOperation uso = new UserServiceOperation();
		uso.login();
		System.out.println("\nExitting from CRS!\n");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
