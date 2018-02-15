package com.epam.finaltask.dao.action;

import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.entity.NameRole;
import com.epam.finaltask.dao.entity.User;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.logic.exception.CommandException;
import org.apache.log4j.Logger;

/**
 * This class represents the actions  for work with user
 */
public class UserAction {
    private static final Logger logger = Logger.getRootLogger();
    public static NameRole addRoleName(int idRole){
        switch (idRole){
            case 2:
                return  NameRole.STUDENT;
            case 3:
                return NameRole.TEACHER;
            default:
                return NameRole.STUDENT;
        }
    }

    public static User changeRole(User user) {
        switch (user.getIdRoleUser()){
            case 2:
                user.setIdRoleUser(3);
                user.setNameRole(NameRole.TEACHER);
                break;
            case 3:
                user.setIdRoleUser(2);
                user.setNameRole(NameRole.STUDENT);
                break;
            default:
                user.setIdRoleUser(2);
                user.setNameRole(NameRole.STUDENT);
        }
        return user;
    }

    public static boolean deleteUser(int idUser) throws CommandException {
        DBDao dbDao = null;
        boolean result = false;
        boolean isDelete = false;
        try {
            DaoType daoType = DaoType.MYSQL;
            dbDao = DBDaoFactory.getInstance().getDao(daoType);

            isDelete = dbDao.deleteUserInCourseStatus(idUser);

            if(isDelete) {
                result = true;
            }
            isDelete = dbDao.deleteUserInCourses(idUser);
            if(isDelete) {
                result = true;
            }
            isDelete = dbDao.deleteTeacher(idUser);
            if(isDelete){
                result = true;
            }
            isDelete = dbDao.deleteUserInResults(idUser);
            if(isDelete){
                result = true;
            }
            isDelete = dbDao.deleteUserInResponses(idUser);
            if(isDelete){
                result = true;
            }
            isDelete = dbDao.deleteUser(idUser);
            if(isDelete){
                result = true;
            } else {
                return false;
            }
        } catch (DBDaoException e) {
            logger.error("DBDaoException is thrown when trying to delete users", e);
            throw new CommandException("DBDaoException is thrown when trying to delete users", e);
        }
        return result;
    }

    public static boolean deleteCourse(int idCourse) throws CommandException {
        DBDao dbDao = null;
        boolean result = false;
        boolean isDelete = false;
        try {
            DaoType daoType = DaoType.MYSQL;
            dbDao = DBDaoFactory.getInstance().getDao(daoType);
            isDelete = dbDao.deleteTask(idCourse);
            if(isDelete){
                result = true;
            }
            isDelete = dbDao.deleteCoursesUsers(idCourse);
            if(isDelete){
                result = true;
            }
            isDelete = dbDao.deleteStatusCourse(idCourse);
            if(isDelete){
                result = true;
            }
            isDelete = dbDao.deleteCourse(idCourse);
            if(isDelete){
                result = true;
            } else {
                return false;
            }
        } catch (DBDaoException e) {
            logger.error("DBDaoException is thrown when trying to delete course", e);
            throw new CommandException("DBDaoException is thrown when trying to delete course", e);
        }
        return result;
    }
}
