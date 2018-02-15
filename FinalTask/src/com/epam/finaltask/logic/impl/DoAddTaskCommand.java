package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.Course;
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
import java.util.List;

/**
 * 	This class executes the add courses
 */
public final class DoAddTaskCommand implements ICommand {
    private static final Logger logger = Logger.getRootLogger();

    private static final String REGEXP_NAME_TASK = "([A-Za-z0-9-\\s]+)";
    private static final String REGEXP_DATE = "(\\d){2}-(\\d){2}-(\\d){4}";


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
            Task task = new Task();
            task.setNameTask(request.getParameter(RequestParameterName.NAME_TASK));
            task.setAssignmentTime(request.getParameter(RequestParameterName.ASSIGNMENT_TIME));
            task.setDeadline(request.getParameter(RequestParameterName.DEADLINE));
            task.setSummary(request.getParameter(RequestParameterName.SUMMARY));
            Integer idCourse = Integer.parseInt(String.valueOf(session.getAttribute(RequestParameterName.ID_COURSE)));
            task.setIdCourse(idCourse);
            request.setAttribute(RequestParameterName.ID_COURSE,idCourse);
            boolean added = dbDao.addTask(task);
            List<Integer> listId = dbDao.getUserId(idCourse);
            if (added){
                task = dbDao.getTask(request.getParameter(RequestParameterName.NAME_TASK));
                page = JspPageName.INDEX_PAGE;
                for(Integer idUser:listId) {
                    boolean changeTasksStatus = dbDao.addTaskStatus(task.getIdTask(), idUser, TaskStatus.NOT_COMPLETED);
                    if(!changeTasksStatus){
                        page = JspPageName.ERROR_PAGE;
                    }
                }
            } else {
                logger.error("DBDaoException is thrown when you try to add the task");
                page = JspPageName.ERROR_PAGE;
            }
        } catch (DBDaoException e){
            logger.error("DBDaoException is thrown when you try to add the task", e);
            throw new CommandException("DBDaoException is thrown when trying to add the task", e);
        }
        return page;
    }
    private boolean validateRequest(HttpServletRequest request){
        if (request.getParameter(RequestParameterName.NAME_TASK) == null){
            return false;
        }
        if (!request.getParameter(RequestParameterName.NAME_TASK).matches(REGEXP_NAME_TASK)){
            logger.error("Name task error");
            return false;
        }
        if (request.getParameter(RequestParameterName.ASSIGNMENT_TIME) == null){
            return false;
        }
        if (request.getParameter(RequestParameterName.DEADLINE) == null){
            return false;
        }
        if (!request.getParameter(RequestParameterName.DEADLINE).matches(REGEXP_DATE)){
            return false;
        }
        if (!request.getParameter(RequestParameterName.ASSIGNMENT_TIME).matches(REGEXP_DATE)){
            return false;
        }
        if (request.getParameter(RequestParameterName.SUMMARY) == null){
            return false;
        }

        return true;
    }

    private String generateURL(HttpServletRequest request){
        StringBuffer url = request.getRequestURL().append("?");
        url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
        url.append("&").append(RequestParameterName.ID_TASK).append("=").append(request.getParameter(RequestParameterName.ID_TASK));
        url.append("&").append(RequestParameterName.SUMMARY).append("=").append(request.getParameter(RequestParameterName.SUMMARY));
        url.append("&").append(RequestParameterName.ASSIGNMENT_TIME).append("=").append(request.getParameter(RequestParameterName.ASSIGNMENT_TIME));
        url.append("&").append(RequestParameterName.DEADLINE).append("=").append(request.getParameter(RequestParameterName.DEADLINE));
        return url.toString();
    }
}
