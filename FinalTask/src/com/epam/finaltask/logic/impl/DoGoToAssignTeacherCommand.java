package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.Course;
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
 * 	This class executes a go to page to assign teacher
 */
public final class DoGoToAssignTeacherCommand implements ICommand {
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
			Integer idCourse = Integer.parseInt(request.getParameter(RequestParameterName.ID_COURSE));
			if (Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER)) > ((dbDao.getNumberOfUsers() - 1)/3 + 1)){
				page = JspPageName.ERROR_PAGE;
				return page;
			}
			Course course = dbDao.getCourse(idCourse);
			if (course == null){
				page = JspPageName.ERROR_PAGE;
			} else {
				int numberOfCourses = dbDao.getNumberOfAllCourses();
				List<User> teacher = dbDao.getTeacher(Integer.parseInt(RequestParameterName.PAGE_NUMBER));
				int size =  teacher.size();
				page = JspPageName.ASSIGN_TEACHER_PAGE;
				request.setAttribute(RequestParameterName.PAGE_NUMBER, request.getParameter(RequestParameterName.PAGE_NUMBER));
				request.setAttribute(RequestParameterName.NUMBER_OF_COURSES, numberOfCourses);
				request.setAttribute(RequestParameterName.TEACHERS, teacher);
				request.setAttribute(RequestParameterName.SIZE, size);
				request.setAttribute(RequestParameterName.COURSE, course);
			}
		} catch (DBDaoException e){
			logger.error("DBDaoException is thrown when trying to go to change the teacher", e);
			throw new CommandException("DBDaoException is thrown when trying to go to change the teacher", e);
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
		return true;
	}
	private String generateURL(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
		url.append("&").append(RequestParameterName.ID_COURSE).append("=").append(request.getParameter(RequestParameterName.ID_COURSE));
		url.append("&").append(RequestParameterName.PAGE_NUMBER).append("=").append(request.getParameter(RequestParameterName.PAGE_NUMBER));
		return url.toString();
	}
}
