package com.epam.finaltask.controller.command;

import java.util.HashMap;
import java.util.Map;

import com.epam.finaltask.logic.impl.DoShowResponsesCommand;
import com.epam.finaltask.logic.impl.*;
import org.apache.log4j.Logger;

import com.epam.finaltask.logic.ICommand;

/**
 *	This class is used to implement the design pattern Command.
 *	It contains Map, in which the key is the name of the command, and the value of a class that executes the command
 */
public class CommandHelper {
	private static final Logger logger = Logger.getRootLogger();
	
	private static final CommandHelper instance = new CommandHelper();
	
	private final Map<CommandName, ICommand> commands = new HashMap<CommandName, ICommand>();
	
	public CommandHelper(){
		commands.put(CommandName.LOGIN, new DoLoginCommand());
		commands.put(CommandName.LOGOUT, new DoLogoutCommand());
		commands.put(CommandName.GO_TO_REGISTRATION, new DoGoToRegistrationCommand());
		commands.put(CommandName.BACK_TO_HOME_PAGE, new DoBackToHomePageCommand());
		commands.put(CommandName.REGISTRATION_USER, new DoRegistrationUserCommand());
		commands.put(CommandName.CHANGE_LOCAL, new DoChangeLocalCommand());
		commands.put(CommandName.NO_SUCH_COMMAND, new DoNoSuchCommand());
		commands.put(CommandName.SHOW_USERS, new DoShowUsersCommand());
		commands.put(CommandName.ADD_COURSE, new DoAddCourseCommand());
		commands.put(CommandName.GO_TO_ADD_COURSE, new DoGoToAddCourseCommand());
		commands.put(CommandName.ADD_RESULT, new DoAddResultCommand());
		commands.put(CommandName.GO_TO_ADD_RESULT, new DoGoToAddResultCommand());
		commands.put(CommandName.ADD_TASK, new DoAddTaskCommand());
		commands.put(CommandName.GO_TO_ADD_TASK, new DoGoToAddTaskCommand());
		commands.put(CommandName.CHOICE_TEACHER, new DoGoToAssignTeacherCommand());
		commands.put(CommandName.ASSIGN_TEACHER, new DoAssignTeacherCommand());
		commands.put(CommandName.SHOW_COURSES, new DoShowCoursesCommand());
		commands.put(CommandName.SHOW_COURSE, new DoShowCourseCommand());
		commands.put(CommandName.SHOW_MY_COURSES, new DoShowMyCoursesCommand());
		commands.put(CommandName.SHOW_MY_COURSE, new DoShowMyCourseCommand());
		commands.put(CommandName.SHOW_MY_TASK, new DoShowMyTaskCommand());
		commands.put(CommandName.LEAVE_COURSE, new DoLeaveCourseCommand());
		commands.put(CommandName.GO_TO_EXECUTE_TASK, new DoGoToExecuteTaskCommand());
		commands.put(CommandName.EXECUTE_TASK, new DoExecuteTaskCommand());
		commands.put(CommandName.GO_TO_REGISTRATION_ON_COURSE, new DoGoRegistrationOnCourseCommand());
		commands.put(CommandName.CHANGE_ROLE, new DoChangeRoleCommand());
		commands.put(CommandName.DELETE_USER, new DoDeleteUserCommand());
		commands.put(CommandName.GO_TO_CHANGE_COURSE, new DoGoToChangeCourseCommand());
		commands.put(CommandName.CHANGE_COURSE, new DoChangeCourseCommand());
		commands.put(CommandName.DELETE_COURSE, new DoDeleteCourseCommand());
		commands.put(CommandName.SHOW_TEACHERS, new DoShowTeachersCommand());
		commands.put(CommandName.SHOW_TEACHERS_COURSES, new DoShowTeachersCoursesCommand());
		commands.put(CommandName.SHOW_RESPONSES, new DoShowResponsesCommand());
	}
	
	public static CommandHelper getInstance(){
		return instance;
	}
	
	public ICommand getCommand(String name){
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e){
			logger.error("IllegalArgumentException is thrown when trying to get command by name. Incorrect parameter in 'command'", e);
			commandName = CommandName.NO_SUCH_COMMAND;
		}
		ICommand command;
		if (commandName != null){
			command = commands.get(commandName);
		} else{
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}		
		return command;
	}
}
