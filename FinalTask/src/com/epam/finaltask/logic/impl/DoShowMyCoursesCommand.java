package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.User;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 	This class executes a show user's courses
 */
public final class DoShowMyCoursesCommand implements ICommand {
	private static final Logger logger = Logger.getRootLogger();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		if (!validateRequest(request)){
			page = JspPageName.ERROR_PAGE;
			return page;
		}

		DBDao dbDao = null;
		try{
			HttpSession session = request.getSession(true);
			String url = generateURL(request);
			session.setAttribute(RequestParameterName.URL, url);
			DaoType daoType = DaoType.MYSQL;
			dbDao = DBDaoFactory.getInstance().getDao(daoType);
			int idUser = ((User)session.getAttribute(RequestParameterName.USER)).getIdUser();
			Integer pageNumber = Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER));
			if (Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER)) > ((dbDao.getNumberOfCourses(idUser) - 1)/2 + 1)){
				page = JspPageName.ERROR_PAGE;
				return page;
			}
			List<Object> courses = dbDao.getCourses(idUser, pageNumber);
			int numberOfCourses = dbDao.getNumberOfCourses(idUser);
			int numberOfPage = (numberOfCourses - 1)/2 + 1;
			page = JspPageName.MY_COURSES_PAGE;
			request.setAttribute(RequestParameterName.CURRENT_NUMBER_PAGE, pageNumber);
			request.setAttribute(RequestParameterName.NUMBER_OF_PAGE, numberOfPage);
			request.setAttribute(RequestParameterName.COURSES, courses);
		} catch (DBDaoException e){
			logger.error("DBDaoException is thrown when trying to show my courses", e);
			throw new CommandException("DBDaoException is thrown when trying to show my courses", e);
		}
		return page;
	}
	
	private boolean validateRequest(HttpServletRequest request){
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
		return true;
	}
	
	private String generateURL(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
		url.append("&").append(RequestParameterName.PAGE_NUMBER).append("=").append(request.getParameter(RequestParameterName.PAGE_NUMBER));
		return url.toString();
	}
}
