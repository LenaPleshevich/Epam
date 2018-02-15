package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.*;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	This class executes a show user's task
 */
public final class DoShowMyTaskCommand implements ICommand {
	private static final Logger logger = Logger.getRootLogger();

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
			Integer idUser = ((User)request.getSession(true).getAttribute(RequestParameterName.USER)).getIdUser();
			Integer idTask = Integer.parseInt(request.getParameter(RequestParameterName.ID_TASK));
			Integer idCourse = Integer.parseInt(request.getParameter(RequestParameterName.ID_COURSE));
			Task task = dbDao.getTask(idTask);
			if (Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER)) > ((dbDao.getNumberOfTasks(idCourse) - 1)/3 + 1)){
				page = JspPageName.ERROR_PAGE;
				return page;
			}
			TaskStatus taskStatus = (dbDao.getTaskStatus(idTask, idUser));
			task.setTaskStatus(taskStatus);

			if(taskStatus.toString().equals("PASSED")) {
				Response responseTask= dbDao.getResponse(idTask, idUser);
				Result result = dbDao.getResult(responseTask.getIdResponse());
				session.setAttribute(RequestParameterName.RESULT, result);
			}

			if (taskStatus == null){
				page = JspPageName.ERROR_PAGE;
			} else {			
				page = JspPageName.MY_TASK_PAGE;
				request.setAttribute(RequestParameterName.PAGE_NUMBER, request.getParameter(RequestParameterName.PAGE_NUMBER));
				request.setAttribute(RequestParameterName.TASK, task);
				request.setAttribute(RequestParameterName.ID_COURSE, idCourse);
			}
		} catch (DBDaoException e){
			logger.error("DBDaoException is thrown when trying to show my task", e);
			throw new CommandException("DBDaoException is thrown when trying to show my task", e);
		}
		return page;
	}

	private boolean validateRequest(HttpServletRequest request){
		if (request.getParameter(RequestParameterName.ID_TASK) == null){
			return false;
		}
		try{
			if (Integer.parseInt(request.getParameter(RequestParameterName.ID_TASK)) <= 0){
				return false;
			}
		} catch (NumberFormatException e){
			logger.error("NumberFormatException is thrown when trying to parse the parameter '" + RequestParameterName.ID_TASK + "'", e);
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
		url.append("&").append(RequestParameterName.ID_TASK).append("=").append(request.getParameter(RequestParameterName.ID_TASK));
		url.append("&").append(RequestParameterName.PAGE_NUMBER).append("=").append(request.getParameter(RequestParameterName.PAGE_NUMBER));
		return url.toString();
	}
}
