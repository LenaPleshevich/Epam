package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.Response;
import com.epam.finaltask.dao.entity.Result;
import com.epam.finaltask.dao.entity.Task;
import com.epam.finaltask.dao.entity.TaskStatus;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	This class executes the add courses
 */
public final class DoAddResultCommand implements ICommand {
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
            DaoType daoType = DaoType.MYSQL;
            dbDao = DBDaoFactory.getInstance().getDao(daoType);
            if (url.equals(session.getAttribute(RequestParameterName.URL))){
                page = JspPageName.INDEX_PAGE;
                return page;
            }
            Result result = new Result();
            Integer idResponse = Integer.parseInt(request.getParameter(RequestParameterName.ID_RESPONSE));
            Response response1 = dbDao.getResponse(idResponse);
            result.setResponse(response1);
            result.setMark(Integer.parseInt(request.getParameter(RequestParameterName.MARK)));
            result.setFeedback(request.getParameter(RequestParameterName.FEEDBACK));
            boolean changedStatusTask = dbDao.changeTaskStatus(response1.getTask().getIdTask(),response1.getUser().getIdUser(), TaskStatus.PASSED);
            boolean added = dbDao.addResult(result);
            if (changedStatusTask){
                page = JspPageName.INDEX_PAGE;
            } else {
                logger.error("DBDaoException is thrown when you try to change status");
                page = JspPageName.ERROR_PAGE;
            }
            if (added){
                page = JspPageName.INDEX_PAGE;
            } else {
                logger.error("DBDaoException is thrown when you try to add the result");
                page = JspPageName.ERROR_PAGE;
            }
        } catch (DBDaoException e){
            logger.error("DBDaoException is thrown when you try to add the result", e);
            throw new CommandException("DBDaoException is thrown when trying to add the result", e);
        }
        return page;
    }
    private boolean validateRequest(HttpServletRequest request){
        if (request.getParameter(RequestParameterName.MARK) == null){
            return false;
        }
        if ((Integer.parseInt(request.getParameter(RequestParameterName.MARK))<=0)||(Integer.parseInt(request.getParameter(RequestParameterName.MARK))>10)){
            return false;
        }
        if (request.getParameter(RequestParameterName.FEEDBACK) == null){
            return false;
        }
        return true;
    }

    private String generateURL(HttpServletRequest request){
        StringBuffer url = request.getRequestURL().append("?");
        url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
        url.append("&").append(RequestParameterName.MARK).append("=").append(request.getParameter(RequestParameterName.MARK));
        url.append("&").append(RequestParameterName.FEEDBACK).append("=").append(request.getParameter(RequestParameterName.FEEDBACK));
        return url.toString();
    }
}
