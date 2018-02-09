package com.epam.finaltask.dao.mysql;

/**
 * 	This class contains constant strings SQL-requests
 */
public final class SQLRequest {
	private SQLRequest(){}
	public static final String SQL_REQUEST_LEAVE_COURSE 						= "DELETE FROM mydb.m2m_users_courses WHERE (course_id=?) and (user_id =?);";
	public static final String SQL_REQUEST_INSERT_USER 							= "INSERT INTO mydb.users (email, password, first_name, last_name, role_id) VALUES (?, ?, ?, ?, '2');";
	public static final String SQL_REQUEST_UPDATE_TEACHER						= "UPDATE mydb.courses SET teacher_id=? WHERE course_id=? ;";
	public static final String SQL_REQUEST_GET_USER 							= "SELECT * FROM mydb.users WHERE (email=?);";
	public static final String SQL_REQUEST_GET_USER_BY_ID						= "SELECT * FROM mydb.users WHERE (user_id=?);";
	public static final String SQL_REQUEST_GET_USERS							= "SELECT * FROM mydb.users WHERE (role_id='2') OR (role_id = '3');";
	public static final String SQL_REQUEST_GET_COURSES							= "SELECT * FROM mydb.courses WHERE (teacher_id='0');";
	public static final String SQL_REQUEST_GET_COURSES_BY_ID_COURSE				= "SELECT * FROM mydb.courses WHERE (course_id=?);";
	public static final String SQL_REQUEST_GET_TASKS_BY_ID_COURSE				= "SELECT * FROM mydb.tasks WHERE (course_id=?);";
	public static final String SQL_REQUEST_GET_RESULT_BY_ID_RESPONSE			= "SELECT * FROM mydb.results WHERE (response_id=?);";
	public static final String SQL_REQUEST_GET_TASK_BY_ID_TASK					= "SELECT * FROM mydb.tasks WHERE (task_id=?);";
	public static final String SQL_REQUEST_GET_RESPONSE_BY_ID_TASK				= "SELECT * FROM mydb.responses WHERE (task_id=?) and (user_id =?);";
	public static final String SQL_REQUEST_GET_USERS_COURSES_BY_ID_COURSE		= "SELECT * FROM mydb.m2m_users_courses WHERE (course_id=?) and (user_id =?);";
	public static final String SQL_REQUEST_GET_MY_COURSES 						= "SELECT * FROM mydb.courses INNER JOIN mydb.m2m_users_courses WHERE (courses.course_id = mydb.m2m_users_courses.course_id) AND (mydb.m2m_users_courses.user_id = ?) LIMIT ?, 10;";
	public static final String SQL_REQUEST_GET_NUMBER_OF_COURSE_BY_ID_USER 		= "SELECT COUNT(course_id) AS numberOfCourses FROM mydb.m2m_users_courses WHERE (user_id=?);";
	public static final String SQL_REQUEST_GET_NUMBER_OF_USERS					= "SELECT COUNT(user_id) AS numberOfUsers FROM mydb.users WHERE (role_id='2') OR (role_id ='3');";
	public static final String SQL_REQUEST_GET_NUMBER_OF_COURSES				= "SELECT COUNT(course_id) AS numberOfCourses FROM mydb.courses;";
	public static final String SQL_REQUEST_GET_STATUS_BY_ID_TASK_AND_ID_USER 	= "SELECT * FROM mydb.course_status WHERE (task_id=?) and (user_id =?);";
	public static final String SQL_REQUEST_INSERT_COURSE_USER 					= "INSERT INTO mydb.m2m_users_courses (course_id, user_id) VALUES (?, ?);";
	public static final String SQL_REQUEST_INSERT_COURSE						= "INSERT INTO mydb.courses (name, description, students_number, start_date, end_date, teacher_id, status ) VALUES (?, ?, ?, ?, ?, '0', ?);";
	public static final String SQL_REQUEST_INSERT_TASK_STATUS 					= "INSERT INTO mydb.course_status (task_id, user_id, status) VALUES (?, ?,?);";
	public static final String SQL_REQUEST_INSERT_RESPONSE						= "INSERT INTO mydb.responses (user_id, task_id, text) VALUES ( ?, ?, ?);";
	public static final String SQL_REQUEST_UPDATE_STATUS_TASK					= "UPDATE mydb.course_status SET status=? WHERE task_id=? and user_id = ? ;";
	public static final String SQL_REQUEST_UPDATE_USER_ROLE						= "UPDATE mydb.users SET role_id=? WHERE user_id = ? ;";
	public static final String SQL_REQUEST_DELETE_TEACHER						= "UPDATE mydb.courses SET teacher_id=0 WHERE teacher_id = ? ;";
	public static final String SQL_REQUEST_DELETE_USER_IN_COURSES				= "DELETE FROM mydb.m2m_users_courses WHERE user_id =?;";
	public static final String SQL_REQUEST_DELETE_USER							= "DELETE FROM mydb.users WHERE user_id =?;";
	public static final String SQL_REQUEST_DELETE_USER_IN_RESPONSES				= "DELETE FROM mydb.responses WHERE user_id =?;";
	public static final String SQL_REQUEST_DELETE_USER_IN_COURSE_STATUS			= "DELETE FROM mydb.course_status WHERE user_id =?;";
	public static final String SQL_REQUEST_DELETE_USER_IN_RESULT				= "DELETE mydb.results,mydb.responses FROM mydb.results INNER JOIN mydb.responses WHERE (mydb.results.response_id = mydb.responses.response_id) AND (mydb.responses.user_id = ?);";

}
