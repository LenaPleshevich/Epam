package com.epam.finaltask.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.mysql.MySQLDBDao;
import com.epam.finaltask.dao.mysql.exception.MySQLDBDaoException;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener.
 * This listener is used to initialize a pool of connections when downloading an application and closing the pool of connections when the application is closed
 *
 */
public class MyServletContextListener implements ServletContextListener {
	private static final Logger logger = Logger.getRootLogger();

    /**
     * Default constructor. 
     */
    public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	try {
			MySQLDBDao.init();
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when initializing the connection pool", e);			
			servletContextEvent.getServletContext().setAttribute(RequestParameterName.EXCEPTION, e);
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
         try {
			MySQLDBDao.destroy();
		} catch (SQLException e) {
			logger.error("SQLException is thrown when destroying the connection pool", e);			
			servletContextEvent.getServletContext().setAttribute(RequestParameterName.EXCEPTION, e);
		}
    }
	
}
