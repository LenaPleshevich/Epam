package com.epam.finaltask.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.finaltask.logic.exception.CommandException;

/**
 * 	This class is an interface that executes the commands received by a request
 */
public interface ICommand {
	String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
