package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 	This class performs a assignment teacher
 */
public final class DoAssignTeacherCommand implements ICommand {
	private static final Logger logger = Logger.getRootLogger();
	
	private static final String COMMAND = "go_to_assign_teacher";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		if (!validateRequest(request)){
			page = JspPageName.ERROR_PAGE;
			return page;
		}
		DBDao dbDao = null;
		try {
			HttpSession session = request.getSession(true);
			String url = generateURL(request);
			session.setAttribute(RequestParameterName.URL, url);
			
			DaoType daoType = DaoType.MYSQL;
			dbDao = DBDaoFactory.getInstance().getDao(daoType);
			Integer idCourse = Integer.parseInt(request.getParameter(RequestParameterName.ID_COURSE));
			Integer idTeacher = Integer.parseInt(request.getParameter(RequestParameterName.ID_TEACHER));
			if (Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER)) > ((dbDao.getNumberOfUsers() - 1)/10 + 1)){
				page = JspPageName.ERROR_PAGE;
				return page;
			}
			boolean assigned = dbDao.addTeacher(idCourse, idTeacher);
			if (assigned){
				response.sendRedirect(generateURLForSendRedirect(request));		
			} else {
				page = JspPageName.ERROR_PAGE;
			}
		} catch (DBDaoException e){
			logger.error("DBDaoException is thrown when trying to assign the teacher ", e);
			throw new CommandException("DBDaoException is thrown when trying to assign the teacher", e);
		} catch (IOException e) {
			logger.error("IOException is thrown when trying to add the teacher", e);
			throw new CommandException("IOException is thrown when trying to add the teacher", e);
		}
		return page;
	}
	
	private boolean validateRequest(HttpServletRequest request){
		if (request.getParameter(RequestParameterName.ID_COURSE) == null){
			return false;
		}
		try{
			if (Integer.parseInt(request.getParameter(RequestParameterName.ID_COURSE)) <= 0){
				return false;
			}
		} catch (NumberFormatException e){
			logger.error("NumberFormatException is thrown when trying to parse the parameter '" + RequestParameterName.ID_COURSE + "'", e);
			return false;
		}
		if (request.getParameter(RequestParameterName.PAGE_NUMBER) == null){
			return false;
		}
		try{
			if (Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER)) <= 0){
				return false;
			}
		} catch (NumberFormatException e){
			logger.error("NumberFormatException is thrown when trying to parse the parameter '" + RequestParameterName.PAGE_NUMBER + "'", e);
			return false;
		}
		if (request.getParameter(RequestParameterName.ID_TEACHER) == null){
			return false;
		}
		try{
			if (Integer.parseInt(request.getParameter(RequestParameterName.ID_TEACHER)) < 0){
				return false;
			}
		} catch (NumberFormatException e){
			logger.error("NumberFormatException is thrown when trying to parse the parameter '" + RequestParameterName.ID_TEACHER + "'", e);
			return false;
		}
		return true;
	}
	private String generateURL(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
		url.append("&").append(RequestParameterName.ID_COURSE).append("=").append(request.getParameter(RequestParameterName.ID_COURSE));
		url.append("&").append(RequestParameterName.PAGE_NUMBER).append("=").append(request.getParameter(RequestParameterName.PAGE_NUMBER));
		url.append("&").append(RequestParameterName.ID_TEACHER).append("=").append(request.getParameter(RequestParameterName.ID_TEACHER));
		return url.toString();
	}
	private String generateURLForSendRedirect(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(COMMAND);
		url.append("&").append(RequestParameterName.PAGE_NUMBER).append("=").append(request.getParameter(RequestParameterName.PAGE_NUMBER));
		url.append("&").append(RequestParameterName.ID_COURSE).append("=").append(request.getParameter(RequestParameterName.ID_COURSE));
		return url.toString();
	}
}
