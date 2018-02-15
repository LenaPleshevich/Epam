package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.Response;
import com.epam.finaltask.dao.entity.Task;
import com.epam.finaltask.dao.entity.TaskStatus;
import com.epam.finaltask.dao.entity.User;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	This class executes the send task
 */
public final class DoExecuteTaskCommand implements ICommand {
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
            Response response1 = new Response();
            Integer idUser = ((User)request.getSession(true).getAttribute(RequestParameterName.USER)).getIdUser();
            Integer idTask = Integer.parseInt(request.getParameter(RequestParameterName.ID_TASK));
            response1.setText(request.getParameter(RequestParameterName.TEXT));
            User user = dbDao.getUser(idUser);
            response1.setUser(user);
            Task task = dbDao.getTask(idTask);
            response1.setTask(task);
            boolean added = dbDao.addResponse(response1);
            boolean changeStatus = dbDao.changeTaskStatus(idTask,idUser, TaskStatus.CHECKING);
            if(changeStatus) {
                task.setTaskStatus(TaskStatus.CHECKING);
                if (added) {
                    page = JspPageName.MY_TASK_PAGE;
                    request.setAttribute(RequestParameterName.PAGE_NUMBER, request.getParameter(RequestParameterName.PAGE_NUMBER));
                    request.setAttribute(RequestParameterName.TASK, task);
                    request.setAttribute(RequestParameterName.ID_COURSE, task.getIdCourse());
                } else {
                    logger.error("DBDaoException is thrown when you try to add a response");
                    page = JspPageName.ERROR_PAGE;
                }
            } else {
                logger.error("DBDaoException is thrown when you try to change status");
                page = JspPageName.ERROR_PAGE;
            }
        } catch (DBDaoException e){
            logger.error("DBDaoException is thrown when you try to add a response", e);
            throw new CommandException("DBDaoException is thrown when trying to add a response", e);
        }

        return page;
    }
    private boolean validateRequest(HttpServletRequest request){
        if (request.getParameter(RequestParameterName.TEXT) == null){
            return false;
        }
        return true;
    }

    private String generateURL(HttpServletRequest request){
        StringBuffer url = request.getRequestURL().append("?");
        url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
        url.append("&").append(RequestParameterName.ID_TASK).append("=").append(request.getParameter(RequestParameterName.ID_TASK));
      return url.toString();
    }
}
