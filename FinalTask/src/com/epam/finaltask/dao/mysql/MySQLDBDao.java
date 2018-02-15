package com.epam.finaltask.dao.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.epam.finaltask.dao.DBDaoFactory;
import com.epam.finaltask.dao.DaoType;
import com.epam.finaltask.dao.action.UserAction;
import com.epam.finaltask.dao.entity.*;
import org.apache.log4j.Logger;

import com.epam.finaltask.dao.DBDao;
import com.epam.finaltask.dao.exception.DBDaoException;
import com.epam.finaltask.dao.mysql.exception.MySQLDBDaoException;

/**
 * This class implements the DAO layer. It contains methods for working directly with the database
 */
public class MySQLDBDao implements DBDao{
	private static final Logger logger = Logger.getRootLogger();
	private static final MySQLDBDao instance = new MySQLDBDao();
	private static MySQLDBPoolConnection poolConnections;	
			
	public static MySQLDBDao getInstance() throws MySQLDBDaoException{
		return instance;
	}
	public static void init() throws MySQLDBDaoException{
		poolConnections = new MySQLDBPoolConnection();
	}
	public static void destroy() throws SQLException{
		poolConnections.destroy();
	}
	
	@Override
	public boolean addUser(User user) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_INSERT_USER);
			preparedStatement.setString(1, user.getEmailUser());
			preparedStatement.setString(2, user.getPasswordUser());
			preparedStatement.setString(3, user.getFirstNameUser());
			preparedStatement.setString(4, user.getLastNameUser());
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_USER, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_USER, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		return false;
	}

	@Override
	public boolean addCourse(Course course) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_INSERT_COURSE);
			preparedStatement.setString(1, course.getNameCourse());
			preparedStatement.setString(2, course.getDescription());
			preparedStatement.setInt(3, course.getMaxNumberStudentsCourse());
			preparedStatement.setString(4, course.getStartDateCourse());
			preparedStatement.setString(5,course.getEndDateCourse());
			preparedStatement.setString(6, course.getStatusCourse());
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			} else {
				logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_COURSE);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		}  finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		logger.error("SQLException is thrown when closing a prepared statement");

		return false;
	}

	@Override
	public boolean addTask(Task task) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_INSERT_TASK);
			preparedStatement.setInt(1, task.getIdCourse());
			preparedStatement.setString(2, task.getAssignmentTime());
			preparedStatement.setString(3, task.getDeadline());
			preparedStatement.setString(4, task.getSummary());
			preparedStatement.setString(5, task.getNameTask());
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_TASK, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_TASK, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		}  finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		logger.error("SQLException is thrown when closing a prepared statement");

		return false;
	}

	@Override
	public boolean addTeacher(int idCourse, int idTeacher) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_UPDATE_TEACHER);
			preparedStatement.setInt(1, idTeacher);
			preparedStatement.setInt(2,	idCourse);
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_UPDATE_TEACHER, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_UPDATE_TEACHER, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		return false;
	}

	@Override
	public boolean addTeacherInCourses(int idCourse, int idTeacher) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_UPDATE_TEACHER_IN_COURSES);
			preparedStatement.setInt(1, idTeacher);
			preparedStatement.setInt(2,	idCourse);
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_UPDATE_TEACHER_IN_COURSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_UPDATE_TEACHER_IN_COURSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		return false;
	}

	@Override
	public boolean addResponse(Response response) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_INSERT_RESPONSE);
			preparedStatement.setInt(1, response.getUser().getIdUser());
			preparedStatement.setInt(2, response.getTask().getIdTask());
			preparedStatement.setString(3, response.getText());
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_RESPONSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_RESPONSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		}  finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		logger.error("SQLException is thrown when closing a prepared statement");

		return false;
	}

	@Override
	public boolean addResult(Result result) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_INSERT_RESULT);
			preparedStatement.setInt(1, result.getResponse().getIdResponse());
			preparedStatement.setInt(2, result.getMark());
			preparedStatement.setString(3, result.getFeedback());
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_RESULT, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_RESULT, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		}  finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		logger.error("SQLException is thrown when closing a prepared statement");

		return false;
	}

	@Override
	public boolean leaveCourse(int idCourse, int idUser) throws DBDaoException {
		boolean isLeaved = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_LEAVE_COURSE);
			preparedStatement.setInt(1, idCourse);
			preparedStatement.setInt(2, idUser);
			int leaved = preparedStatement.executeUpdate();
			if (leaved == 1){
				isLeaved = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_LEAVE_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_LEAVE_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isLeaved;
	}

	@Override
	public boolean deleteUserInResponses(int idUser) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_USER_IN_RESPONSES);
			preparedStatement.setInt(1, idUser);
			int updated = preparedStatement.executeUpdate();
			if(updated > 0) {
				isDelete = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER_IN_RESPONSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER_IN_RESPONSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean deleteUserInCourses(int idUser) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_USER_IN_COURSES);
			preparedStatement.setInt(1, idUser);
			int deleted = preparedStatement.executeUpdate();
			if(deleted > 0) {
				isDelete = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER_IN_COURSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER_IN_COURSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean deleteUser(int idUser) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_USER);
			preparedStatement.setInt(1, idUser);
			int updated = preparedStatement.executeUpdate();
			if (updated == 1){
				isDelete = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean deleteCourse(int idCourse) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_COURSE);
			preparedStatement.setInt(1, idCourse);
			int updated = preparedStatement.executeUpdate();
			if (updated == 1){
				isDelete = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean deleteTask(int idCourse) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_TASKS);
			preparedStatement.setInt(1, idCourse);
			int deleted = preparedStatement.executeUpdate();
			if(deleted > 0) {
				isDelete = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_TASKS, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_TASKS, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean deleteStatusCourse(int idCourse) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_STATUS_COURSE);
			preparedStatement.setInt(1, idCourse);
			int deleted = preparedStatement.executeUpdate();
			if(deleted > 0) {
				isDelete = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_STATUS_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_STATUS_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean deleteCoursesUsers(int idCourse) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_USERS_COURSES);
			preparedStatement.setInt(1, idCourse);
			int deleted = preparedStatement.executeUpdate();
			if(deleted > 0){
				isDelete = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USERS_COURSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USERS_COURSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean deleteUserInCourseStatus(int idUser) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_USER_IN_COURSE_STATUS);
			preparedStatement.setInt(1, idUser);
			int deleted = preparedStatement.executeUpdate();
			if(deleted > 0){
				isDelete = false;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER_IN_COURSE_STATUS, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER_IN_COURSE_STATUS, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean deleteUserInResults(int idUser) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_USER_IN_RESULT);
			preparedStatement.setInt(1, idUser);
			int deleted = preparedStatement.executeUpdate();
			if(deleted > 0) {
				isDelete = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER_IN_RESULT, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_USER_IN_RESULT, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean deleteTeacher(int idUser) throws DBDaoException {
		boolean isDelete = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_DELETE_TEACHER);
			preparedStatement.setInt(1, idUser);
			int deleted = preparedStatement.executeUpdate();
			if(deleted > 0) {
				isDelete = true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_TEACHER, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_DELETE_TEACHER, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return isDelete;
	}

	@Override
	public boolean changeCourse(Course course) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_CHANGE_COURSE);
			preparedStatement.setString(1, course.getStartDateCourse());
			preparedStatement.setString(2, course.getEndDateCourse());
			preparedStatement.setInt(3, course.getMaxNumberStudentsCourse());
			preparedStatement.setString(4, course.getStatusCourse());
			preparedStatement.setString(5, course.getDescription());
			preparedStatement.setInt(6, course.getIdCourse());
			int changed = preparedStatement.executeUpdate();
			if (changed == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_CHANGE_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_CHANGE_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		return false;
	}

	@Override
	public boolean changeRole(User user) throws DBDaoException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try{
				connection = poolConnections.getConnection();
				preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_UPDATE_USER_ROLE);
				preparedStatement.setInt(1, user.getIdRoleUser());
				preparedStatement.setInt(2,user.getIdUser());
				int added = preparedStatement.executeUpdate();
				if (added == 1){
					return true;
				}
			} catch (SQLException e){
				logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_UPDATE_USER_ROLE, e);
				throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_UPDATE_USER_ROLE, e);
			} catch (MySQLDBDaoException e){
				logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
			} finally {
				try{
					if (preparedStatement != null){
						if (!preparedStatement.isClosed()){
							preparedStatement.close();
						}
					}
					if (connection != null){
						poolConnections.putConnection(connection);
					}
				} catch (SQLException e) {
					logger.error("SQLException is thrown when closing a prepared statement", e);
					throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
				}
			}
			return false;
		}

	@Override
	public boolean registrationOnCourse(int idCourse, int idUser) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_INSERT_COURSE_USER);
			preparedStatement.setInt(1, idCourse);
			preparedStatement.setInt(2, idUser);
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_COURSE_USER, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_COURSE_USER, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		return false;
	}

	@Override
	public boolean checkRegistration(int idCourse, int idUser) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean result = true;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_USERS_COURSES_BY_ID_COURSE);
			preparedStatement.setInt(1, idCourse);
			preparedStatement.setInt(2, idUser);
			ResultSet resultSet = preparedStatement.executeQuery();
			result = resultSet.next();
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USERS_COURSES_BY_ID_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USERS_COURSES_BY_ID_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		return result;
	}

	@Override
	public boolean changeTaskStatus(int idTask, int idUser, TaskStatus taskStatus) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_UPDATE_STATUS_TASK);
			preparedStatement.setString(1, taskStatus.toString());
			preparedStatement.setInt(2, idTask);
			preparedStatement.setInt(3, idUser);
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_UPDATE_STATUS_TASK, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_UPDATE_STATUS_TASK, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		return false;
	}

	@Override
	public boolean addTaskStatus(int idTask, int idUser, TaskStatus taskStatus) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_INSERT_TASK_STATUS);
			preparedStatement.setInt(1, idTask);
			preparedStatement.setInt(2, idUser);
			preparedStatement.setString(3, taskStatus.toString());
			int added = preparedStatement.executeUpdate();
			if (added == 1){
				return true;
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_TASK_STATUS, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_INSERT_TASK_STATUS, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException is thrown when closing a prepared statement", e);
				throw new DBDaoException("SQLException is thrown when closing a prepared statement", e);
			}
		}
		return false;
	}

	@Override
	public int getNumberOfTeachers() throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int numberOfTeachers = 0;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_NUMBER_OF_TEACHERS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				numberOfTeachers = resultSet.getInt(DBColumnName.NUMBER_OF_TEACHERS);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_TEACHERS, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_TEACHERS, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return numberOfTeachers;
	}

	@Override
	public User getUser(String email) throws DBDaoException {
		User newUser = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;	
		try{			
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_USER);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();			
			while (resultSet.next()){			
				newUser = new User();
				newUser.setPasswordUser(resultSet.getString(DBColumnName.PASSWORD_USER));
				newUser.setEmailUser(resultSet.getString(DBColumnName.EMAIL_USER));
				newUser.setFirstNameUser(resultSet.getString(DBColumnName.FIRST_NAME_USER));
				newUser.setLastNameUser(resultSet.getString(DBColumnName.LAST_NAME_USER));
				newUser.setIdRoleUser(resultSet.getInt(DBColumnName.ROLE_USER));
				newUser.setIdUser(resultSet.getInt(DBColumnName.ID_USER));
				newUser.setNameRole(UserAction.addRoleName(resultSet.getInt(DBColumnName.ROLE_USER)));
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USER, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USER, e);	
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}				
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return newUser;
	}

	@Override
	public User getUser(int idUser) throws DBDaoException {
		User newUser = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;	
		try{			
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_USER_BY_ID);
			preparedStatement.setInt(1, idUser);
			resultSet = preparedStatement.executeQuery();			
			while (resultSet.next()){			
				newUser = new User();
				newUser.setPasswordUser(resultSet.getString(DBColumnName.PASSWORD_USER));
				newUser.setEmailUser(resultSet.getString(DBColumnName.EMAIL_USER));
				newUser.setFirstNameUser(resultSet.getString(DBColumnName.FIRST_NAME_USER));
				newUser.setLastNameUser(resultSet.getString(DBColumnName.LAST_NAME_USER));
				newUser.setIdRoleUser(resultSet.getInt(DBColumnName.ROLE_USER));
				newUser.setIdUser(resultSet.getInt(DBColumnName.ID_USER));
				newUser.setNameRole(UserAction.addRoleName(resultSet.getInt(DBColumnName.ROLE_USER)));
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USER_BY_ID, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USER_BY_ID, e);	
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}				
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return newUser;	
	}

	@Override
	public Course getCourse(int idCourse) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Course course = new Course();
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_COURSES_BY_ID_COURSE);
			preparedStatement.setInt(1, idCourse);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				course.setNameCourse(resultSet.getString(DBColumnName.NAME_COURSE));
				course.setMaxNumberStudentsCourse(resultSet.getInt(DBColumnName.MAX_NUMBER_STUDENT_COURSE));
				course.setDescription(resultSet.getString(DBColumnName.DESCRIPTION));
				course.setIdTeacher(resultSet.getInt(DBColumnName.ID_TEACHER));
				course.setStartDateCourse(resultSet.getString(DBColumnName.START_DATE_COURSE));
				course.setEndDateCourse(resultSet.getString(DBColumnName.END_DATE_COURSE));
				course.setStatusCourse(resultSet.getString(DBColumnName.STATUS_COURSE));
				course.setIdCourse(resultSet.getInt(DBColumnName.ID_COURSE));
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_COURSES_BY_ID_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_COURSES_BY_ID_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return course;
	}

	@Override
	public List<Integer> getUserId(int idCourse) throws DBDaoException {
		List<Integer> listId = new ArrayList<Integer>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Integer idUser;
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_USER_BY_ID_COURSE);
			preparedStatement.setInt(1, idCourse);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				idUser = resultSet.getInt(DBColumnName.ID_USER);
				listId.add(idUser);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USER_BY_ID_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USER_BY_ID_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return listId;
	}

	@Override
	public List<Course> getAllCourses(int pageNumber) throws DBDaoException {
		List<Course> courses = new ArrayList<Course>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Course course;
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_COURSES);
			preparedStatement.setInt(1, (pageNumber-1)*2);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				course = new Course();
				course.setStartDateCourse(resultSet.getString(DBColumnName.START_DATE_COURSE));
				course.setEndDateCourse(resultSet.getString(DBColumnName.END_DATE_COURSE));
				course.setMaxNumberStudentsCourse(resultSet.getInt(DBColumnName.MAX_NUMBER_STUDENT_COURSE));
				course.setStatusCourse(resultSet.getString(DBColumnName.STATUS_COURSE));
				course.setIdTeacher(resultSet.getInt(DBColumnName.ID_TEACHER));
				course.setNameCourse(resultSet.getString(DBColumnName.NAME_COURSE));
				course.setIdCourse(resultSet.getInt(DBColumnName.ID_COURSE));
				course.setDescription(resultSet.getString(DBColumnName.DESCRIPTION));
				courses.add(course);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_COURSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_COURSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return courses;
	}

	@Override
	public List<Task> getTasksOfCourse(int idCourse, int pageNumber) throws DBDaoException {
		List<Task> tasks = new ArrayList<Task>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Task task;
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_TASKS_BY_ID_COURSE);
			preparedStatement.setInt(1, idCourse);
			preparedStatement.setInt(2, (pageNumber-1)*3);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				task = new Task();
				task.setNameTask(resultSet.getString(DBColumnName.NAME_TASK));
				task.setSummary(resultSet.getString(DBColumnName.SUMMARY));
				task.setAssignmentTime(resultSet.getString(DBColumnName.ASSIGNMENT_TIME));
				task.setDeadline(resultSet.getString(DBColumnName.DEADLINE));
				task.setIdCourse(resultSet.getInt(DBColumnName.ID_COURSE));
				task.setIdTask(resultSet.getInt(DBColumnName.ID_TASK));
				tasks.add(task);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TASKS_BY_ID_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TASKS_BY_ID_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return tasks;
	}

	@Override
	public List<Task> getAllTasksOfCourse(int idCourse) throws DBDaoException {
		List<Task> tasks = new ArrayList<Task>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Task task;
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_TASKS);
			preparedStatement.setInt(1, idCourse);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				task = new Task();
				task.setNameTask(resultSet.getString(DBColumnName.NAME_TASK));
				task.setSummary(resultSet.getString(DBColumnName.SUMMARY));
				task.setAssignmentTime(resultSet.getString(DBColumnName.ASSIGNMENT_TIME));
				task.setDeadline(resultSet.getString(DBColumnName.DEADLINE));
				task.setIdCourse(resultSet.getInt(DBColumnName.ID_COURSE));
				task.setIdTask(resultSet.getInt(DBColumnName.ID_TASK));
				tasks.add(task);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TASKS, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TASKS, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return tasks;
	}

	@Override
	public List<Object> getTeachersCourses(int idUser, int pageNumber) throws DBDaoException {
		List<Object> courses = new ArrayList<Object>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Course course;
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_TEACHERS_COURSES);
			preparedStatement.setInt(1, idUser);
			preparedStatement.setInt(2, (pageNumber - 1)*2);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				course = new Course();
				course.setNameCourse(resultSet.getString(DBColumnName.NAME_COURSE));
				course.setMaxNumberStudentsCourse(resultSet.getInt(DBColumnName.MAX_NUMBER_STUDENT_COURSE));
				course.setDescription(resultSet.getString(DBColumnName.DESCRIPTION));
				course.setIdTeacher(resultSet.getInt(DBColumnName.ID_TEACHER));
				course.setStartDateCourse(resultSet.getString(DBColumnName.START_DATE_COURSE));
				course.setEndDateCourse(resultSet.getString(DBColumnName.END_DATE_COURSE));
				course.setStatusCourse(resultSet.getString(DBColumnName.STATUS_COURSE));
				course.setIdCourse(resultSet.getInt(DBColumnName.ID_COURSE));
				courses.add(course);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TEACHERS_COURSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TEACHERS_COURSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return courses;
	}

	@Override
	public List<Object> getCourses(int idUser, int pageNumber) throws DBDaoException {
		List<Object> courses = new ArrayList<Object>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Course course;
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_MY_COURSES);
			preparedStatement.setInt(1, idUser);
			preparedStatement.setInt(2, (pageNumber - 1)*2);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				course = new Course();
				course.setNameCourse(resultSet.getString(DBColumnName.NAME_COURSE));
				course.setMaxNumberStudentsCourse(resultSet.getInt(DBColumnName.MAX_NUMBER_STUDENT_COURSE));
				course.setDescription(resultSet.getString(DBColumnName.DESCRIPTION));
				course.setIdTeacher(resultSet.getInt(DBColumnName.ID_TEACHER));
				course.setStartDateCourse(resultSet.getString(DBColumnName.START_DATE_COURSE));
				course.setEndDateCourse(resultSet.getString(DBColumnName.END_DATE_COURSE));
				course.setStatusCourse(resultSet.getString(DBColumnName.STATUS_COURSE));
				course.setIdCourse(resultSet.getInt(DBColumnName.ID_COURSE));
				courses.add(course);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_MY_COURSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_MY_COURSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return courses;
	}

	@Override
	public List<User> getUsers(int pageNumber) throws DBDaoException {
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			User user;
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_USERS);
			preparedStatement.setInt(1, (pageNumber - 1)*3);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				user = new User();
				user.setEmailUser(resultSet.getString(DBColumnName.EMAIL_USER));
				user.setFirstNameUser(resultSet.getString(DBColumnName.FIRST_NAME_USER));
				user.setLastNameUser(resultSet.getString(DBColumnName.LAST_NAME_USER));
				user.setIdUser(resultSet.getInt(DBColumnName.ID_USER));
				user.setIdRoleUser(resultSet.getInt(DBColumnName.ID_ROLE));
				user.setNameRole(UserAction.addRoleName(user.getIdRoleUser()));
				users.add(user);
			}			
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USERS, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_USERS, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}				
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return users;
	}

	@Override
	public List<User> getTeacher(int pageNumber) throws DBDaoException {
		List<User> teachers = new ArrayList<User>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			User user;
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_TEACHERS);
			preparedStatement.setInt(1, (pageNumber-1)*3);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				user = new User();
				user.setEmailUser(resultSet.getString(DBColumnName.EMAIL_USER));
				user.setFirstNameUser(resultSet.getString(DBColumnName.FIRST_NAME_USER));
				user.setLastNameUser(resultSet.getString(DBColumnName.LAST_NAME_USER));
				user.setIdUser(resultSet.getInt(DBColumnName.ID_USER));
				user.setIdRoleUser(resultSet.getInt(DBColumnName.ID_ROLE));
				user.setNameRole(UserAction.addRoleName(user.getIdRoleUser()));
				teachers.add(user);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TEACHERS, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TEACHERS, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return teachers;
	}

	@Override
	public List<Response> getResponses(int pageNumber, int idUser) throws DBDaoException {
		DBDao dbDao = null;
		List<Response> responses = new ArrayList<Response>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			DaoType daoType = DaoType.MYSQL;
			dbDao = DBDaoFactory.getInstance().getDao(daoType);
			Response response;
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_RESPONSES);
			preparedStatement.setInt(1, idUser);
			preparedStatement.setInt(2, (pageNumber-1)*3);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				response = new Response();
				response.setIdResponse(resultSet.getInt(DBColumnName.ID_RESPONSE));
				User user = dbDao.getUser(resultSet.getInt(DBColumnName.ID_USER));
				Task task = dbDao.getTask(resultSet.getInt(DBColumnName.ID_TASK));
				Result result = dbDao.getResult(response.getIdResponse());
				response.setTask(task);
				response.setUser(user);
				response.setText(resultSet.getString(DBColumnName.TEXT_RESPONSE));
				if(result == null) {
					responses.add(response);
				}
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_RESPONSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_RESPONSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return responses;
	}

	@Override
	public int getNumberOfUsers() throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;	
		int numberOfUsers = 0;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_NUMBER_OF_USERS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				numberOfUsers = resultSet.getInt(DBColumnName.NUMBER_OF_USERS);				
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_USERS, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_USERS, e);	
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}				
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return numberOfUsers;
	}

	@Override
	public int getNumberOfResponses(int idUser) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int numberOfResponses = 0;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_NUMBER_OF_RESPONSES);
			preparedStatement.setInt(1, idUser);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				numberOfResponses = resultSet.getInt(DBColumnName.NUMBER_OF_RESPONSES);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_RESPONSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_RESPONSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return numberOfResponses;
	}

	@Override
	public int getNumberOfCourses(int idUser) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int numberOfCourses = 0;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_NUMBER_OF_COURSE_BY_ID_USER);
			preparedStatement.setInt(1, idUser);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				numberOfCourses = resultSet.getInt(DBColumnName.NUMBER_OF_COURSES);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_COURSE_BY_ID_USER, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_COURSE_BY_ID_USER, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return numberOfCourses;
	}

	@Override
	public int getNumberOfTasks(int idCourse) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int numberOfTasks = 0;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_NUMBER_OF_TASK_BY_ID_COURSE);
			preparedStatement.setInt(1, idCourse);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				numberOfTasks = resultSet.getInt(DBColumnName.NUMBER_OF_TASKS);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_TASK_BY_ID_COURSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_TASK_BY_ID_COURSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return numberOfTasks;
	}

	@Override
	public int getNumberOfAllCourses() throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int numberOfCourses = 0;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_NUMBER_OF_COURSES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				numberOfCourses = resultSet.getInt(DBColumnName.NUMBER_OF_COURSES);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_COURSES, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_NUMBER_OF_COURSES, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return numberOfCourses;
	}

	@Override
	public Result getResult(int idResponse) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Result result = null;
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_RESULT_BY_ID_RESPONSE);
			preparedStatement.setInt(1, idResponse);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				result = new Result();
				result.setFeedback(resultSet.getString(DBColumnName.FEEDBACK));
				result.setMark(resultSet.getInt(DBColumnName.MARK));
				result.setIdResult(resultSet.getInt(DBColumnName.ID_RESULT));
				Response response = new Response();
				response.setIdResponse(resultSet.getInt(DBColumnName.ID_RESPONSE));
				result.setResponse(response);
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_RESULT_BY_ID_RESPONSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_RESULT_BY_ID_RESPONSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return result;
	}

	@Override
	public Response getResponse(int idResponse) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Response response = null;
		DBDao dbDao = null;
		try{
			DaoType daoType = DaoType.MYSQL;
			dbDao = DBDaoFactory.getInstance().getDao(daoType);
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_RESPONSE_BY_ID_RESPONSE);
			preparedStatement.setInt(1, idResponse);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				response = new Response();
				Integer idUser = resultSet.getInt(DBColumnName.ID_USER);
				User user = dbDao.getUser(idUser);
				response.setUser(user);
				Integer idTask = resultSet.getInt(DBColumnName.ID_TASK);
				Task task = dbDao.getTask(idTask);
				response.setTask(task);
				response.setIdResponse(resultSet.getInt(DBColumnName.ID_RESPONSE));
				response.setText(resultSet.getString(DBColumnName.TEXT_RESPONSE));
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_RESPONSE_BY_ID_RESPONSE, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_RESPONSE_BY_ID_RESPONSE, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return response;
	}

	@Override
	public Response getResponse(int idTask, int idUser) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Response response = new Response();
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_RESPONSE_BY_ID_TASK);
			preparedStatement.setInt(1, idTask);
			preparedStatement.setInt(2, idUser);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				response.setIdResponse(resultSet.getInt(DBColumnName.ID_RESPONSE));
				response.setText(resultSet.getString(DBColumnName.TEXT_RESPONSE));
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_RESPONSE_BY_ID_TASK, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_RESPONSE_BY_ID_TASK, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return response;
	}

	@Override
	public Task getTask(int idTask) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Task task = new Task();
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_TASK_BY_ID_TASK);
			preparedStatement.setInt(1, idTask);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				task.setIdCourse(resultSet.getInt(DBColumnName.ID_COURSE));
				task.setSummary(resultSet.getString(DBColumnName.SUMMARY));
				task.setNameTask(resultSet.getString(DBColumnName.NAME_TASK));
				task.setIdTask(resultSet.getInt(DBColumnName.ID_TASK));
				task.setDeadline(resultSet.getString(DBColumnName.DEADLINE));
				task.setAssignmentTime(resultSet.getString(DBColumnName.ASSIGNMENT_TIME));
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TASK_BY_ID_TASK, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TASK_BY_ID_TASK, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return task;
	}

	@Override
	public Task getTask(String nameTask) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Task task = new Task();
		try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_TASK_BY_NAME_TASK);
			preparedStatement.setString(1, nameTask);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				task.setIdCourse(resultSet.getInt(DBColumnName.ID_COURSE));
				task.setSummary(resultSet.getString(DBColumnName.SUMMARY));
				task.setNameTask(resultSet.getString(DBColumnName.NAME_TASK));
				task.setIdTask(resultSet.getInt(DBColumnName.ID_TASK));
				task.setDeadline(resultSet.getString(DBColumnName.DEADLINE));
				task.setAssignmentTime(resultSet.getString(DBColumnName.ASSIGNMENT_TIME));
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TASK_BY_NAME_TASK, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_TASK_BY_NAME_TASK, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return task;
	}

	@Override
	public TaskStatus getTaskStatus(int idTask, int idUser) throws DBDaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		TaskStatus taskStatus = null;
			try{
			connection = poolConnections.getConnection();
			preparedStatement = connection.prepareStatement(SQLRequest.SQL_REQUEST_GET_STATUS_BY_ID_TASK_AND_ID_USER);
			preparedStatement.setInt(1, idTask);
			preparedStatement.setInt(2, idUser);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
			taskStatus = TaskStatus.valueOf(resultSet.getString(DBColumnName.STATUS));
			}
		} catch (SQLException e){
			logger.error("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_STATUS_BY_ID_TASK_AND_ID_USER, e);
			throw new DBDaoException("SQLException is thrown when trying to execute the query: " + SQLRequest.SQL_REQUEST_GET_STATUS_BY_ID_TASK_AND_ID_USER, e);
		} catch (MySQLDBDaoException e){
			logger.error("MySQLDBDaoException is thrown when receiving a connection", e);
			throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection", e);
		} finally {
			try{
				if (preparedStatement != null){
					if (!preparedStatement.isClosed()){
						preparedStatement.close();
					}
				}
				if (resultSet != null){
					if (!resultSet.isClosed()){
						resultSet.close();
					}
				}
				if (connection != null){
					poolConnections.putConnection(connection);
				}
			} catch (SQLException e) {
				logger.error("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
				throw new DBDaoException("MySQLDBDaoException is thrown when receiving a connection or a result set", e);
			}
		}
		return taskStatus;
	}
}
