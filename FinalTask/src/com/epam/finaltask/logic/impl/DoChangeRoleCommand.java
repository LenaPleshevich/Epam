package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.action.UserAction;
import com.epam.finaltask.dao.entity.User;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import com.epam.finaltask.tag.JSPListBean;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 	This class executes a role change
 */
public final class DoChangeRoleCommand implements ICommand {
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
			Integer pageNumber = Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER));
			Integer idUser = Integer.parseInt(request.getParameter(RequestParameterName.ID_USER));
			User user = dbDao.getUser(idUser);
			user = UserAction.changeRole(user);
			if(dbDao.changeRole(user)) {
				int numberOfUsers = dbDao.getNumberOfUsers();
				int numberOfPage = (numberOfUsers - 1)/3 + 1;
				request.setAttribute(RequestParameterName.CURRENT_NUMBER_PAGE, pageNumber);
				request.setAttribute(RequestParameterName.NUMBER_OF_PAGE, numberOfPage);
				List<User> users = dbDao.getUsers(pageNumber);
				JSPListBean jspListBean = new JSPListBean(users);
				request.setAttribute(RequestParameterName.USERS, jspListBean);
				page = JspPageName.USERS_PAGE;
			} else {
				page = JspPageName.ERROR_PAGE;
			}
		} catch (DBDaoException e){
			logger.error("DBDaoException is thrown when trying to change role", e);
			throw new CommandException("DBDaoException is thrown when trying to change role", e);
		}
		return page;
	}
	
	private String generateURL(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
		url.append("&").append(RequestParameterName.PAGE_NUMBER).append("=").append(request.getParameter(RequestParameterName.PAGE_NUMBER));
		return url.toString();
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
}
