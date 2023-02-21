package com.signify.jdbc;
import com.signify.bean.*;
import java.util.*;
/**
 * @author dp201
 *
 */
public class UserData {
	public static HashMap<Integer, Student> students = new HashMap<Integer, Student>();
	public static HashMap<Integer, Professor> professors = new HashMap<Integer, Professor>();
	public static HashMap<Integer, Admin> admins = new HashMap<Integer, Admin>();
	public static HashMap<String, Course> courses = new HashMap<String, Course>();
	public static HashMap<Integer, Payment> payments = new HashMap<Integer, Payment>();
	public static HashMap<Integer, User> users = new HashMap<Integer, User>();
}
