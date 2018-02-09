package com.epam.finaltask.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.epam.finaltask.dao.mysql.exception.MySQLDBDaoException;

/**
 * 	This class implements a connection pool
 */
public class MySQLDBPoolConnection {
	private static final Logger logger = Logger.getRootLogger();
	
	private DBResourceManager resource = DBResourceManager.getInstance();
	private String url 		= resource.getValue(DBParameter.DB_URL);
	private String user 	= resource.getValue(DBParameter.DB_USER);
	private String password = resource.getValue(DBParameter.DB_PASSWORD);
	private String driver 	= resource.getValue(DBParameter.DB_DRIVER);
	private int    size 	= Integer.parseInt(resource.getValue(DBParameter.DB_SIZE));
	private BlockingQueue<Connection> connections;
	
	public MySQLDBPoolConnection() throws MySQLDBDaoException{
		try{
			Class.forName(driver);
			connections = new ArrayBlockingQueue<Connection>(size);
			for (int i = 0; i < size; i++){
				connections.add(DriverManager.getConnection(this.url, this.user, this.password));
			}
		} catch (ClassNotFoundException e){
			logger.error("ClassNotFoundException is thrown when the class is loaded in memory", e);
			throw new MySQLDBDaoException("ClassNotFoundException is thrown when the class is loaded in memory", e);
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to create a connection", e);
			throw new MySQLDBDaoException("SQLException is thrown when trying to create a connection", e);
		}
	}
	public MySQLDBPoolConnection(String url, String user, String password) throws MySQLDBDaoException{
		try{
			Class.forName(resource.getValue(driver));			
			this.url = url;
			this.user = user;
			this.password = password;
			connections = new ArrayBlockingQueue<Connection>(size);
			for (int i = 0; i < size; i++){
				connections.add(DriverManager.getConnection(this.url, this.user, this.password));
			}
		} catch (ClassNotFoundException e){
			logger.error("ClassNotFoundException is thrown when the class is loaded in memory", e);
			throw new MySQLDBDaoException("ClassNotFoundException is thrown when the class is loaded in memory", e);
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to create a connection", e);
			throw new MySQLDBDaoException("SQLException is thrown when trying to create a connection", e);
		}
	}
	
	public Connection getConnection() throws MySQLDBDaoException{
		try{
			return connections.take();
		} catch (InterruptedException e){
			logger.error("InterruptedException is thrown when returning a connection", e);
			throw new MySQLDBDaoException("InterruptedException is thrown when returning a connection", e);
		}
	}
	
	public void putConnection(Connection connection){
		connections.offer(connection);
	}
	
	public void destroy() throws SQLException{
		for (Connection connection: connections){
			if (connection != null){
				if (!connection.isClosed()){
					connection.close();
				}
			}
		}
	}
	
}
