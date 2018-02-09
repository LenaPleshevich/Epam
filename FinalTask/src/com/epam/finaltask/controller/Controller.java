package com.epam.finaltask.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.finaltask.controller.command.CommandHelper;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;

/**
 * Servlet implementation class Controller
 */
public final class Controller extends HttpServlet {
	private static final Logger logger = Logger.getRootLogger();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	private void errorMessageDireclyFromResponse(HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		response.getWriter().println("E R R O R !!!");
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if (request.getAttribute(RequestParameterName.EXCEPTION) != null){			
			request.setAttribute(RequestParameterName.MESSAGE_ABOUT_ERROR, true);
			request.getRequestDispatcher(JspPageName.ERROR_PAGE).forward(request, response);
		}
		if (request.getAttribute(RequestParameterName.INCORRECT_ACTION) != null){
			boolean param = (boolean)request.getAttribute(RequestParameterName.INCORRECT_ACTION);
			if (param == true){
				request.setAttribute(RequestParameterName.MESSAGE_INCORRECT_ACTION, true);
				request.getRequestDispatcher(JspPageName.ERROR_PAGE).forward(request, response);
			}
		} else {
			String nameCommand = request.getParameter(RequestParameterName.COMMAND);
			ICommand command = CommandHelper.getInstance().getCommand(nameCommand);
			String page = null;	
			try{			
				page = command.execute(request, response);
			} catch (CommandException e){
				page = JspPageName.ERROR_PAGE;
				logger.error("CommandException is thrown. Forward to " + page, e);
			} catch (Exception e){
				page = JspPageName.ERROR_PAGE;
				logger.error("Exception is thrown. Forward to "  + page, e);
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
			if (requestDispatcher != null){
				requestDispatcher.forward(request, response);
			} else {
				errorMessageDireclyFromResponse(response);
			}	
		}
	}
}
