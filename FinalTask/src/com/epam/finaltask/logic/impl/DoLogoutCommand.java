package com.epam.finaltask.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;

/**
 * 	This class executes a logout
 */

public final class DoLogoutCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page;
		HttpSession session = request.getSession(true);
		String url = generateURL(request);		
		session.setAttribute(RequestParameterName.URL, url);
		session.setAttribute(RequestParameterName.IS_LOGGED, false);
		session.removeAttribute(RequestParameterName.USER);
		page = JspPageName.INDEX_PAGE;
		return page;
	}
	private String generateURL(HttpServletRequest request){
		StringBuffer url = request.getRequestURL().append("?");
		url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
		return url.toString();
	}
}
