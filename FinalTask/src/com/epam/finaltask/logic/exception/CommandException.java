package com.epam.finaltask.logic.exception;

/**
 * 	This class is an exception that throws Logic layer
 */
public class CommandException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CommandException(String msg) {
		super(msg);
	}
	public CommandException(String msg, Exception e){
		super(msg, e);
	}
}
