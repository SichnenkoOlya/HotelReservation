package by.bsuir.archive.service;

import by.bsuir.archive.service.impl.ConnectionServiceImpl;
import by.bsuir.archive.service.impl.MatterServiceImpl;
import by.bsuir.archive.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory factory = new ServiceFactory();

	private final MatterService matterService = new MatterServiceImpl();
	private final UserService userService = new UserServiceImpl();
	private final ConnectionService connectionService = new ConnectionServiceImpl();

	public static ServiceFactory getInstance() {
		return factory;
	}

	public MatterService getMatterService() {
		return matterService;
	}
	
	public ConnectionService getConnectionService() {
		return connectionService;
	}

	public UserService getUserService() {
		return userService;
	}
}
