package com.epam.finaltask.dao.mysql.exception;

/**
 * This class is an exception that throws MySQL layer
 */

public class MySQLDBDaoException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public MySQLDBDaoException(String msg) {
		super(msg);
	}
	public MySQLDBDaoException(String msg, Exception e){
		super(msg, e);
	}
}
