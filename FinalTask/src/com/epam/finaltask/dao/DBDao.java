package com.epam.finaltask.dao;

import java.util.List;

import com.epam.finaltask.dao.entity.*;
import com.epam.finaltask.dao.exception.DBDaoException;

/**
 * 	This class is an interface containing methods of work with the database
 */
public interface DBDao {
	
	boolean 		addUser(User user)					 									throws DBDaoException;
	boolean 		addCourse(Course course)												throws DBDaoException;
	boolean 		addTask(Task task)  													throws DBDaoException;
	boolean 		addTeacher(int idCourse, int idTeacher)									throws DBDaoException;
	boolean 		addTeacherInCourses(int idCourse, int idTeacher)						throws DBDaoException;
	boolean 		addResponse(Response response)  	 									throws DBDaoException;
	boolean 		addResult(Result result)												throws DBDaoException;
	boolean 		leaveCourse(int idCourse, int idUser)									throws DBDaoException;
	boolean 		deleteUserInResponses(int idUser)										throws DBDaoException;
	boolean 		deleteUserInCourses(int idUser)											throws DBDaoException;
	boolean 		deleteUser(int idUser)													throws DBDaoException;
	boolean 		deleteCourse(int idCourse)												throws DBDaoException;
	boolean 		deleteTask(int idCourse)												throws DBDaoException;
	boolean 		deleteStatusCourse(int idCourse)										throws DBDaoException;
	boolean 		deleteCoursesUsers(int idCourse)										throws DBDaoException;
	boolean 		deleteUserInCourseStatus(int idUser)									throws DBDaoException;
	boolean 		deleteUserInResults(int idUser)			    							throws DBDaoException;
	boolean 		deleteTeacher(int idUser)				    							throws DBDaoException;
	boolean 		changeCourse(Course course)												throws DBDaoException;
	boolean 		changeRole(User user)  													throws DBDaoException;
	boolean 		registrationOnCourse(int idCourse, int idUser)							throws DBDaoException;
	boolean 		checkRegistration(int idCourse, int idUser)								throws DBDaoException;
	boolean 		changeTaskStatus(int idTask, int idUser, TaskStatus taskStatus)			throws DBDaoException;
	boolean 		addTaskStatus(int idTask, int idUser, TaskStatus taskStatus)			throws DBDaoException;

	int 			getNumberOfTeachers()					  								throws DBDaoException;
	int 			getNumberOfUsers()														throws DBDaoException;
	int 			getNumberOfResponses(int idUser)										throws DBDaoException;
	int 			getNumberOfCourses(int idUser)	  										throws DBDaoException;
	int				getNumberOfTasks(int idCourse)											throws DBDaoException;
	int 			getNumberOfAllCourses()													throws DBDaoException;

	Result			getResult(int idResponse)												throws DBDaoException;
	Response		getResponse(int idResponse)												throws DBDaoException;
	Response		getResponse(int idTask, int idUser)										throws DBDaoException;
	Task			getTask(int idTask)														throws DBDaoException;
	Task			getTask(String nameTask)												throws DBDaoException;
	TaskStatus	    getTaskStatus(int idTask, int idUser)									throws DBDaoException;

	
	User			getUser(String email)	 					throws DBDaoException;
	User			getUser(int idUser)							throws DBDaoException;
	Course			getCourse(int idCourse)						throws DBDaoException;

	List<Integer>	getUserId(int idCourse)						throws DBDaoException;
	List<Course>	getAllCourses(int pageNumber)				throws DBDaoException;
	List<Task>  	getTasksOfCourse(int idCourse, int pageNumber)				throws DBDaoException;
	List<Task>  	getAllTasksOfCourse(int idCourse)				throws DBDaoException;
	List<Object>	getCourses(int idUser, int pageNumber)		throws DBDaoException;
	List<Object>	getTeachersCourses(int idUser, int pageNumber)throws DBDaoException;
	List<User>		getUsers(int pageNumber)					throws DBDaoException;
	List<User>		getTeacher(int pageNumber)					throws DBDaoException;
	List<Response>	getResponses(int pageNumber, int idUser)	throws DBDaoException;


}
