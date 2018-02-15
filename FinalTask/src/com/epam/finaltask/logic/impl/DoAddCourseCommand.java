package com.epam.finaltask.logic.impl;

import com.epam.finaltask.controller.JspPageName;
import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.Course;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.ICommand;
import com.epam.finaltask.logic.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	This class executes the add courses
 */
public final class DoAddCourseCommand implements ICommand {
    private static final Logger logger = Logger.getRootLogger();

    private static final String REGEXP_NAME_COURSE = "([A-Za-z0-9-\\s]+)";
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
            Course course = new Course();
            course.setNameCourse(request.getParameter(RequestParameterName.NAME_COURSE));
            course.setDescription(request.getParameter(RequestParameterName.DESCRIPTION));
            course.setMaxNumberStudentsCourse(Integer.parseInt(request.getParameter(RequestParameterName.MAX_NUMBER_STUDENT_COURSE)));
            course.setStartDateCourse(request.getParameter(RequestParameterName.START_DATE_COURSE));
            course.setEndDateCourse(request.getParameter(RequestParameterName.END_DATE_COURSE));
            course.setStatusCourse(request.getParameter(RequestParameterName.STATUS_COURSE));
            course.setIdTeacher(0);
            boolean added = dbDao.addCourse(course);
            if (added){
                page = JspPageName.INDEX_PAGE;
            } else {
                logger.error("DBDaoException is thrown when you try to add the course");
                page = JspPageName.ERROR_PAGE;
            }
        } catch (DBDaoException e){
            logger.error("DBDaoException is thrown when you try to add the course", e);
            throw new CommandException("DBDaoException is thrown when trying to add the course", e);
        }
        return page;
    }
    private boolean validateRequest(HttpServletRequest request){
        if (request.getParameter(RequestParameterName.NAME_COURSE) == null){
            return false;
        }
        if (!request.getParameter(RequestParameterName.NAME_COURSE).matches(REGEXP_NAME_COURSE)){
            logger.error("Name course error");
            return false;
        }
        if (request.getParameter(RequestParameterName.DESCRIPTION) == null){
            return false;
        }
        if (request.getParameter(RequestParameterName.MAX_NUMBER_STUDENT_COURSE) == null){
            return false;
        }
        try{
            if (Integer.parseInt(request.getParameter(RequestParameterName.MAX_NUMBER_STUDENT_COURSE)) <= 0){
                return false;
            }
        } catch (NumberFormatException e){
            logger.error("NumberFormatException is thrown when trying to parse the parameter '" + RequestParameterName.MAX_NUMBER_STUDENT_COURSE + "'", e);
            return false;
        }
        if (request.getParameter(RequestParameterName.START_DATE_COURSE) == null){
            return false;
        }
        if (!request.getParameter(RequestParameterName.START_DATE_COURSE).matches(REGEXP_DATE)){
            return false;
        }
        if (request.getParameter(RequestParameterName.END_DATE_COURSE) == null){
            return false;
        }
        if (!request.getParameter(RequestParameterName.END_DATE_COURSE).matches(REGEXP_DATE)){
            return false;
        }
        if (request.getParameter(RequestParameterName.STATUS_COURSE) == null){
            return false;
        }

        return true;
    }

    private String generateURL(HttpServletRequest request){
        StringBuffer url = request.getRequestURL().append("?");
        url.append(RequestParameterName.COMMAND).append("=").append(request.getParameter(RequestParameterName.COMMAND));
        url.append("&").append(RequestParameterName.START_DATE_COURSE).append("=").append(request.getParameter(RequestParameterName.START_DATE_COURSE));
        url.append("&").append(RequestParameterName.END_DATE_COURSE).append("=").append(request.getParameter(RequestParameterName.END_DATE_COURSE));
        url.append("&").append(RequestParameterName.MAX_NUMBER_STUDENT_COURSE).append("=").append(request.getParameter(RequestParameterName.MAX_NUMBER_STUDENT_COURSE));
        url.append("&").append(RequestParameterName.NAME_COURSE).append("=").append(request.getParameter(RequestParameterName.NAME_COURSE));
        url.append("&").append(RequestParameterName.DESCRIPTION).append("=").append(request.getParameter(RequestParameterName.DESCRIPTION));
        url.append("&").append(RequestParameterName.STATUS_COURSE).append("=").append(request.getParameter(RequestParameterName.STATUS_COURSE));
        return url.toString();
    }
}
