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
 * 	This class executes a login
 */
public final class DoLoginCommand implements ICommand{
	private static final Logger logger = Logger.getRootLogger();
	
	private static final String REGEXP_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {		
		String 	page = null;
		if (!validateRequest(request)){
			page = JspPageName.ERROR_PAGE;
			return page;
		}
		DBDao	dbDao = null;
		User	user = new User();	
		try{			
			HttpSession session = request.getSession(true);
			String url = generateURL(request);
			session.setAttribute(RequestParameterName.URL, url);
			
			DaoType daoType = DaoType.MYSQL;			
			dbDao = DBDaoFactory.getInstance().getDao(daoType);
			String email = request.getParameter(RequestParameterName.EMAIL).toLowerCase();
			user = dbDao.getUser(email);
			String password = request.getParameter(RequestParameterName.PASSWORD);
			if ((user == null) || (!user.getPasswordUser().equals(MD5Util.codingMD5(password)))){
				page = JspPageName.LOGIN_FAIL_PAGE;
			} else {				
				page = JspPageName.INDEX_PAGE;
				session.setAttribute(RequestParameterName.USER, user);
				session.setAttribute(RequestParameterName.IS_LOGGED, true);				
				session.setAttribute(RequestParameterName.ROLE, user.getIdRoleUser());
			}
			
		} catch (DBDaoException e){
			logger.error("DBDaoException is thrown when trying to login", e);
			throw new CommandException("DBDaoException is thrown when trying to login", e);
		} 
		
		return page;
	}
	
	private String generateURL(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
		url.append("&").append(RequestParameterName.EMAIL).append("=").append(request.getParameter(RequestParameterName.EMAIL));
		url.append("&").append(RequestParameterName.PASSWORD).append("=").append(request.getParameter(RequestParameterName.PASSWORD));
		return url.toString();
	}
	
	private boolean validateRequest(HttpServletRequest request){
		if (request.getParameter(RequestParameterName.EMAIL) == null){
			return false;
		}
		if (!request.getParameter(RequestParameterName.EMAIL).matches(REGEXP_EMAIL)){
			return false;
		}
		if (request.getParameter(RequestParameterName.PASSWORD) == null){
			return false;
		}
		return true;
	}
}
