package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.Task;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	This class executes a go to page to sent task
 */
public final class DoGoToExecuteTaskCommand implements ICommand {
	private static final Logger logger = Logger.getRootLogger();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = JspPageName.EXECUTE_TASK;
		HttpSession session = request.getSession(true);	
		String url = generateURL(request);
		session.setAttribute(RequestParameterName.URL, url);
		DBDao dbDao = null;

		try{
			Integer idTask = Integer.parseInt(request.getParameter(RequestParameterName.ID_TASK));
			DaoType daoType = DaoType.MYSQL;
			dbDao = DBDaoFactory.getInstance().getDao(daoType);
			Task task = dbDao.getTask(idTask);
			request.setAttribute(RequestParameterName.TASK, task);
			request.setAttribute(RequestParameterName.PAGE_NUMBER, request.getParameter(RequestParameterName.PAGE_NUMBER));
		} catch (DBDaoException e){
			logger.error("DBDaoException is thrown when you try to execute a task", e);
			throw new CommandException("DBDaoException is thrown when trying to execute a task", e);
		}
		return page;
	}
	
	private String generateURL(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
		return url.toString();
	}
}
