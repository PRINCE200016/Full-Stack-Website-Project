package com.itrainu.Exception;

/**
 * DatabaseException is propogated by Model classes when an unhandled Database
 * exception occurred.
 * 
 * @author Kirti Singh
 *
 */

public class DatabaseException extends Exception {

	public DatabaseException(String msg) {
		super(msg);
	}

}
