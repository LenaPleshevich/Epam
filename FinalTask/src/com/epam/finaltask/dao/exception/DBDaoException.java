package com.epam.finaltask.dao.exception;

/**
 * This class is an exception that throws DAO layer
 */
public class DBDaoException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DBDaoException(String msg) {
		super(msg);
	}
	public DBDaoException(String msg, Exception e){
		super(msg, e);
	}
}
