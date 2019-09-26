package com.abc.assignment.exceptions;

import javax.persistence.EntityNotFoundException;

public class PersonNotFoundException extends  RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	public PersonNotFoundException(String id) {
		super("Person Id:" + id+" does not exist");
	}
	
	
	
}
