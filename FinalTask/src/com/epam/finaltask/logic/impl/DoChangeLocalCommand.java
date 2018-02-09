package com.epam.finaltask.logic.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.controller.command.CommandName;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;

/**
 * This class executes a change of locale
 */
public final class DoChangeLocalCommand implements ICommand {
	private static final Logger logger = Logger.getRootLogger();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		if (!validateRequest(request)){
			String page = JspPageName.ERROR_PAGE;
			return page;
		}
		request.getSession(true).setAttribute(RequestParameterName.LOCAL, request.getParameter(RequestParameterName.LOCAL));
		String url = (String)request.getSession(true).getAttribute(RequestParameterName.URL);
		try {
			if (url == null){
				request.getRequestDispatcher(JspPageName.INDEX_PAGE).forward(request, response);
			} else {
				if (url.indexOf(CommandName.LOGIN.toString().toLowerCase()) > 0){
					url = url.substring(0, url.indexOf(CommandName.LOGIN.toString().toLowerCase()));
					url = url.concat(CommandName.BACK_TO_HOME_PAGE.toString().toLowerCase());
				}
				if (url.indexOf(CommandName.REGISTRATION_USER.toString().toLowerCase()) > 0){
					url = url.substring(0, url.indexOf(CommandName.REGISTRATION_USER.toString().toLowerCase()));
					url = url.concat(CommandName.BACK_TO_HOME_PAGE.toString().toLowerCase());
				}
				response.sendRedirect(url);
			}
		} catch (IOException e) {
			logger.error("IOException is thrown when trying to change the locale", e);
			throw new CommandException("IOException is thrown when trying to change the locale", e);
		} catch (ServletException e) {
			logger.error("ServletException is thrown when trying to change the locale", e);
			throw new CommandException("ServletException is thrown when trying to change the locale", e);
		}
		return null;
	}
	
	private boolean validateRequest(HttpServletRequest request){
		if (request.getParameter(RequestParameterName.LOCAL) == null){
			return false;
		}
		if ((!request.getParameter(RequestParameterName.LOCAL).equals("ru")) && (!request.getParameter(RequestParameterName.LOCAL).equals("en"))){
			return false;
		}
		return true;
	}
}
