package com.epam.finaltask.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.User;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import com.epam.finaltask.logic.impl.util.MD5Util;

/**
 * 	This class executes a user registration
 */
public final class DoRegistrationUserCommand implements ICommand{
	private static final Logger logger = Logger.getRootLogger();
	
	private static final String REGEXP_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String REGEXP_PASSWORD = "[_A-Za-zА-Яа-я0-9-\\+]+";
	private static final String REGEXP_NAME = "^[A-Za-zА-Яа-я]+$";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		if (!validateRequest(request)){
			page = JspPageName.ERROR_PAGE;
			return page;
		}
		DBDao	dbDao = null;
		try{
			HttpSession session = request.getSession(true);
			String url = generateURL(request);
			DaoType daoType = DaoType.MYSQL;				
			dbDao = DBDaoFactory.getInstance().getDao(daoType);	
			
			if (url.equals(session.getAttribute(RequestParameterName.URL))){
				page = JspPageName.INDEX_PAGE;
				return page;
			}			
			session.setAttribute(RequestParameterName.URL, url);
			
			if (request.getParameter(RequestParameterName.FIRST_PASSWORD).equals(request.getParameter(RequestParameterName.SECOND_PASSWORD))){							
				User user = new User();
				user.setEmailUser(request.getParameter(RequestParameterName.EMAIL).toLowerCase());
				user.setFirstNameUser(request.getParameter(RequestParameterName.FIRST_NAME));
				user.setLastNameUser(request.getParameter(RequestParameterName.LAST_NAME));
				user.setPasswordUser(MD5Util.codingMD5(request.getParameter(RequestParameterName.FIRST_PASSWORD)));
				
				if (dbDao.getUser(user.getEmailUser()) == null){
					if ((user.getFirstNameUser().matches(REGEXP_NAME)) && (user.getLastNameUser().matches(REGEXP_NAME))){
						boolean added = dbDao.addUser(user);			
						if (added){
							page = JspPageName.REGISTRATION_PAGE;
							request.setAttribute(RequestParameterName.MESSAGE_SUCCESSFUL_REGISTRATION, true);
						} else {				
							page = JspPageName.REGISTRATION_PAGE;
						}
					} else {
						page = JspPageName.REGISTRATION_PAGE;
						request.setAttribute(RequestParameterName.MESSAGE_INCORRECT_NAMES, true);
					}
				} else {
					System.out.println(url);
					page = JspPageName.REGISTRATION_PAGE;
					request.setAttribute(RequestParameterName.MESSAGE_BUSY_EMAIL, true);
				}
			} else {
				page = JspPageName.REGISTRATION_PAGE;
				request.setAttribute(RequestParameterName.MESSAGE_INCORRECT_PASSWORD, true);
			}
		} catch (DBDaoException e){
			logger.error("DBDaoException is thrown when trying to registration the user", e);
			throw new CommandException("DBDaoException is thrown when trying to registration the user", e);
		}
		return page;
	}	
	
	private String generateURL(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
		url.append("&").append(RequestParameterName.EMAIL).append("=").append(request.getParameter(RequestParameterName.EMAIL));
		url.append("&").append(RequestParameterName.FIRST_PASSWORD).append("=").append(request.getParameter(RequestParameterName.FIRST_PASSWORD));
		url.append("&").append(RequestParameterName.SECOND_PASSWORD).append("=").append(request.getParameter(RequestParameterName.SECOND_PASSWORD));
		url.append("&").append(RequestParameterName.LAST_NAME).append("=").append(request.getParameter(RequestParameterName.LAST_NAME));
		url.append("&").append(RequestParameterName.FIRST_NAME).append("=").append(request.getParameter(RequestParameterName.FIRST_NAME));
		return url.toString();
	}
	
	private boolean validateRequest(HttpServletRequest request){
		if (request.getParameter(RequestParameterName.FIRST_PASSWORD) == null){
			return false;
		}
		if (!request.getParameter(RequestParameterName.FIRST_PASSWORD).matches(REGEXP_PASSWORD)){
			return false;
		}
		if (request.getParameter(RequestParameterName.SECOND_PASSWORD) == null){
			return false;
		}
		if (request.getParameter(RequestParameterName.EMAIL) == null){
			return false;
		}
		if (!request.getParameter(RequestParameterName.EMAIL).matches(REGEXP_EMAIL)){
			return false;
		}
		if (request.getParameter(RequestParameterName.FIRST_NAME) == null){
			return false;
		}
		if (request.getParameter(RequestParameterName.LAST_NAME) == null){
			return false;
		}
		return true;
	}
}
