package com.epam.finaltask.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.controller.command.CommandName;

/**
 * Servlet Filter implementation class CommandFilter.
 * This filter is used to trap unacceptable actions
 */
public class CommandFilter implements Filter {
	
    /**
     * Default constructor. 
     */
    public CommandFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		catchLogin(request, response);	
		catchLogout(request, response);
		
		chain.doFilter(request, response);
	}
	
	private void catchLogout(ServletRequest request, ServletResponse response) throws IOException{
		HttpSession session = ((HttpServletRequest)request).getSession(true);
		String command = request.getParameter(RequestParameterName.COMMAND).toUpperCase();
		Object objectIsLogged = session.getAttribute(RequestParameterName.IS_LOGGED);
		if (objectIsLogged != null){
			boolean isLogged = Boolean.parseBoolean(objectIsLogged.toString());
			if (command != null){
				if ((command.equals(CommandName.LOGOUT.toString())) && (isLogged != true)){
					StringBuffer url = ((HttpServletRequest)request).getRequestURL().append("?");
					url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
					session.setAttribute(RequestParameterName.URL, url.toString());
					request.setAttribute(RequestParameterName.INCORRECT_ACTION, true);
				}
			}
		}
	}
	
	private void catchLogin(ServletRequest request, ServletResponse response) throws IOException{
		HttpSession session = ((HttpServletRequest)request).getSession(true);
		String command =request.getParameter(RequestParameterName.COMMAND).toUpperCase();
		Object objectIsLogged = session.getAttribute(RequestParameterName.IS_LOGGED);
		if (objectIsLogged != null){
			boolean isLogged = Boolean.parseBoolean(objectIsLogged.toString());
			if (command != null){
				if ((command.equals(CommandName.LOGIN.toString())) && (isLogged == true)){
					StringBuffer url = ((HttpServletRequest)request).getRequestURL().append("?");
					url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
					url.append("&").append(RequestParameterName.EMAIL).append("=").append(request.getParameter(RequestParameterName.EMAIL));
					url.append("&").append(RequestParameterName.PASSWORD).append("=").append(request.getParameter(RequestParameterName.PASSWORD));
					session.setAttribute(RequestParameterName.URL, url.toString());
					request.setAttribute(RequestParameterName.INCORRECT_ACTION, true);
				}
			}
		}
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
