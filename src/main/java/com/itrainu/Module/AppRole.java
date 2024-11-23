package com.itrainu.Module;

import org.apache.catalina.User;

/**
 * The Interface AppRole.
 * 
 * @author Shubham Yadav
 *
 */
public interface AppRole {

	public int GUEST = 0;
	public int STUDENT = 1;
	public int Staff = 2;
	public int ADMIN = 99;
	boolean addUser(User user);
	boolean isValidUser(String username, String password);

	
}
