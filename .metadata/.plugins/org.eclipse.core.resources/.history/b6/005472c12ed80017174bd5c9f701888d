package by.bsuir.archive.controller.command.impl;

import by.bsuir.archive.controller.command.Command;
import by.bsuir.archive.controller.command.exception.CommandException;
import by.bsuir.archive.service.MatterService;
import by.bsuir.archive.service.ServiceFactory;
import by.bsuir.archive.service.exception.ServiceException;

public class CreateMatter implements Command {

	@Override
	public String execute(String request) throws CommandException {
		ServiceFactory servaceFactory = new ServiceFactory();
		MatterService matterServise = servaceFactory.getMatterService();
		String surname = request.split("\\|")[1];
		String name = request.split("\\|")[2];
		int age = Integer.parseInt(request.split("\\|")[3]);
		String university = request.split("\\|")[4];
		int numberOfGroup = Integer.parseInt(request.split("\\|")[5]);
		String response=null;
		try {
			response=matterServise.createMatter(surname, name, age, university, numberOfGroup);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return response;
	}
}
