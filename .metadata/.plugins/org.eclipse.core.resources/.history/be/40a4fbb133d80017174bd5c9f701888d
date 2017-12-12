package by.bsuir.archive.controller.command.impl;

import by.bsuir.archive.controller.command.Command;
import by.bsuir.archive.controller.command.exception.CommandException;
import by.bsuir.archive.service.ServiceFactory;
import by.bsuir.archive.service.UserService;
import by.bsuir.archive.service.exception.ServiceException;

public class Authentication implements Command {

	@Override
	public String execute(String request) throws CommandException {
		ServiceFactory servaceFactory = new ServiceFactory();
		UserService userService = servaceFactory.getUserService();
		String login = request.split("\\|")[1];
		String password = request.split("\\|")[2];
		try {
			userService.authenticate(login, password);
			if (userService.isAdmin(login, password)) {
				return "admin";
			} else
				return "user";
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
	}
}