package by.bsuir.archive.controller;

import by.bsuir.archive.controller.command.Command;
import by.bsuir.archive.controller.command.exception.CommandException;

public class Controller {
	private final CommandProvider provider = new CommandProvider();

	public String doAction(String request) {
		String response = null;
		String commandName = request.split("\\|")[0];
		try {
			Command command = provider.getCommand(commandName);
			response = command.execute(request);
		} catch (CommandException e) {
			response = "error:" + e.getLocalizedMessage() + "\n";
		}
		return response;
	}
}
