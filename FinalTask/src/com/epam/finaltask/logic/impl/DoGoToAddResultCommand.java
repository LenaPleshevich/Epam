package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.Response;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	This class executes a go to page to add result
 */
public final class DoGoToAddResultCommand implements ICommand {

	private static final Logger logger = Logger.getRootLogger();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		DBDao dbDao = null;
		try {
			page = JspPageName.ADD_RESULT_PAGE;
			HttpSession session = request.getSession(true);
			String url = generateURL(request);
			DaoType daoType = DaoType.MYSQL;
			dbDao = DBDaoFactory.getInstance().getDao(daoType);
			session.setAttribute(RequestParameterName.URL, url);
			Integer idResponse = Integer.parseInt(request.getParameter(RequestParameterName.ID_RESPONSE));
			Response newResponse = dbDao.getResponse(idResponse);
			session.setAttribute(RequestParameterName.RESPONSE,newResponse);
			request.setAttribute(RequestParameterName.ID_RESPONSE, idResponse);
			request.setAttribute(RequestParameterName.TASK,newResponse.getTask());
		} catch (DBDaoException e){
			logger.error("DBDaoException is thrown when trying to go to add result ", e);
			throw new CommandException("DBDaoException is thrown when trying to go  to add result", e);
		}
		return page;
	}
	
	private String generateURL(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
		return url.toString();
	}
}
