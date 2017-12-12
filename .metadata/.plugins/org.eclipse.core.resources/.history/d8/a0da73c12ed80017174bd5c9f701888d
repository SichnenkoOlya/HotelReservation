package by.bsuir.archive.controller.command.impl;

import by.bsuir.archive.controller.command.Command;
import by.bsuir.archive.controller.command.exception.CommandException;
import by.bsuir.archive.service.MatterService;
import by.bsuir.archive.service.ServiceFactory;
import by.bsuir.archive.service.exception.ServiceException;

public class GetMatter implements Command {

	@Override
	public String execute(String request) throws CommandException {
		ServiceFactory servaceFactory = new ServiceFactory();
		MatterService matterServise = servaceFactory.getMatterService();
		String response;
		try {
			int idMatter = Integer.parseInt(request.split("\\|")[1]);
			response = matterServise.findMatter(idMatter);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		catch (NumberFormatException e) {
			throw new CommandException("Некорректные данные!");
		}
		return response;
	}
}
