package com.epam.finaltask.dao.mysql;

/**
 * 	This class contains the names of database columns
 */
public final class DBColumnName {
	private DBColumnName(){}
	public static final String PASSWORD_USER 				= "password";
	public static final String EMAIL_USER  					= "email";
	public static final String FIRST_NAME_USER 				= "first_name";
	public static final String LAST_NAME_USER 				= "last_name";
	public static final String ROLE_USER 					= "role_id";
	public static final String ID_USER 						= "user_id";

	public static final String NUMBER_OF_TASKS 				= "numberOfTasks";
	public static final String NUMBER_OF_COURSES 			= "numberOfCourses";
	public static final String NUMBER_OF_USERS 				= "numberOfUsers";

	public static final String STATUS_COURSE 				= "status";
	public static final String MAX_NUMBER_STUDENT_COURSE 	= "students_number";
	public static final String START_DATE_COURSE 			= "start_date";
	public static final String END_DATE_COURSE 				= "end_date";
	public static final String ID_TEACHER					= "teacher_id";
	public static final String NAME_COURSE					= "name";
	public static final String ID_COURSE 					= "course_id";

	public static final String ID_RESULT 					= "result_id";
	public static final String ID_RESPONSE					= "response_id";

	public static final String ID_TASK					= "task_id";
	public static final String SUMMARY					= "summary";
	public static final String STATUS					= "status";
	public static final String NAME_TASK				= "name";
	public static final String ASSIGNMENT_TIME			= "assignment_time";
	public static final String DEADLINE					= "deadline";

	public static final String DESCRIPTION				= "description";
	public static final String FEEDBACK					= "feedback";
	public static final String MARK						= "mark";
	public static final String TEXT_RESPONSE			= "text";
	public static final String ID_ROLE					= "role_id";
	public static final String NAME_ROLE				= "role_name";
}
