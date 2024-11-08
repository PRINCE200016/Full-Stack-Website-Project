package com.itrainu.Exception;

/**
 * ApplicationException is propogated from Model classes when an business logic
 * exception occurered.
 * 
 * @author Kirti Singh
 *
 */
public class ApplicationException extends Exception {

	public ApplicationException(String msg) {
		super(msg);
	}

}
