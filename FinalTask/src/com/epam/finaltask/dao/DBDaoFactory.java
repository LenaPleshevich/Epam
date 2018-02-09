package com.epam.finaltask.dao;

import org.apache.log4j.Logger;

import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.dao.mysql.MySQLDBDao;
import com.epam.finaltask.dao.mysql.exception.MySQLDBDaoException;

/**
 * 	This class is used to implement the design pattern DAO Factory
 */

public class DBDaoFactory {
	private static final Logger logger = Logger.getRootLogger();
	
	private static final DBDaoFactory instance = new DBDaoFactory();
	
	public static DBDaoFactory getInstance(){
		return instance;
	}
	
	public DBDao getDao(DaoType type) throws DBDaoException{
		switch (type){
		case MYSQL:
			try{
				return MySQLDBDao.getInstance();
			} catch (MySQLDBDaoException e){
				logger.error("MySQLDBDaoException is thrown when receiving MySQLDBDao", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving MySQLDBDao", e);
			}
			default:
				throw new DBDaoException("No such DAO.");
		}
	}	
}
