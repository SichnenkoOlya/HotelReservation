package by.bsuir.archive.controller.command.impl;

import by.bsuir.archive.controller.command.Command;
import by.bsuir.archive.controller.command.exception.CommandException;
import by.bsuir.archive.service.MatterService;
import by.bsuir.archive.service.ServiceFactory;
import by.bsuir.archive.service.exception.ServiceException;

public class ChangeMatter implements Command {

	@Override
	public String execute(String request) throws CommandException {
		ServiceFactory servaceFactory = new ServiceFactory();
		MatterService matterServise = servaceFactory.getMatterService();
		String response=null;
		try {
			int idMatter = Integer.parseInt(request.split("\\|")[1]);
			String surname = request.split("\\|")[2];
			String name = request.split("\\|")[3];
			int age = Integer.parseInt(request.split("\\|")[4]);
			String university = request.split("\\|")[5];
			int numberOfGroup = Integer.parseInt(request.split("\\|")[6]);
			response=matterServise.changeMatter(idMatter, surname, name, age, university, numberOfGroup);

		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		catch (NumberFormatException e) {
			throw new CommandException("Некорректные данные!");
		}
		return response;
	}
}
