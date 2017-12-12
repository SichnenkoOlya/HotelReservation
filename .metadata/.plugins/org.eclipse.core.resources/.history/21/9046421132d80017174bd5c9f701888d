package by.bsuir.archive.service.impl;

import by.bsuir.archive.connect.Connection;
import by.bsuir.archive.connect.exception.ConnectionException;
import by.bsuir.archive.service.ConnectionService;
import by.bsuir.archive.service.exception.ServiceException;

public class ConnectionServiceImpl implements ConnectionService {

	@Override
	public void start() throws ServiceException {
		Connection connection=Connection.getInstance();
		try {
			connection.Start();
		} catch (ConnectionException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void stop() throws ServiceException {
		Connection connection=Connection.getInstance();
		try {
			connection.Stop();
		} catch (ConnectionException e) {
			throw new ServiceException();
		}	
	}

}
