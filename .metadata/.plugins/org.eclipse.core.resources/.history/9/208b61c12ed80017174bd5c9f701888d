package by.bsuir.archive.controller.command.impl;

import by.bsuir.archive.controller.command.Command;
import by.bsuir.archive.controller.command.exception.CommandException;
import by.bsuir.archive.service.ConnectionService;
import by.bsuir.archive.service.ServiceFactory;
import by.bsuir.archive.service.exception.ServiceException;

public class StopConnection implements Command {

	@Override
	public String execute(String request) throws CommandException {
		ServiceFactory servaceFactory = new ServiceFactory();
		ConnectionService connectionServise = servaceFactory.getConnectionService();
		try {
			connectionServise.stop();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return "";
	}
}