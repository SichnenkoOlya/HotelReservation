package by.bsuir.archive.dao.impl;

import by.bsuir.archive.connect.Connection;
import by.bsuir.archive.connect.Message;
import by.bsuir.archive.connect.exception.ConnectionException;
import by.bsuir.archive.connect.socket.SocketStream;
import by.bsuir.archive.connect.socket.exception.SocketStreamException;
import by.bsuir.archive.dao.DAOMatter;
import by.bsuir.archive.dao.exception.DAOException;

public class DAOMatterImpl implements DAOMatter {

	private Connection connection = null;
	private SocketStream socket = null;

	@Override
	public String createMatter(String surname, String name, int age, String univer, int numberOfGroup)
			throws DAOException {
		connection = Connection.getInstance();
		try {
			connection.Start();
		} catch (ConnectionException e) {
			throw new DAOException(e);
		}
		socket = connection.getSocket();
		Message message = new Message("CREATE_MATTER", surname, name, age, univer, numberOfGroup);
		String response = null;
		try {
			socket.sendMessage(message);
			message = socket.receiveMessage();
			response = message.getAnswer();
		} catch (SocketStreamException e) {
			throw new DAOException(e);
		}
		return response;
	}

	@Override
	public String findMatter(int idMatter) throws DAOException {
		connection = Connection.getInstance();
		try {
			connection.Start();
		} catch (ConnectionException e) {
			throw new DAOException(e);
		}
		socket = connection.getSocket();
		Message message = new Message("GET_MATTER");
		message.setId(idMatter);
		String response = null;
		try {
			socket.sendMessage(message);
			message = socket.receiveMessage();
			response = message.getAnswer();
		} catch (SocketStreamException e) {
			throw new DAOException(e);
		}
		return response;
	}

	@Override
	public String changeMatter(int idMatter, String newSurname, String newName, int newAge, String newUniver,
			int newNumberOfGroup) throws DAOException {
		connection = Connection.getInstance();
		try {
			connection.Start();
		} catch (ConnectionException e) {
			throw new DAOException(e);
		}
		socket = connection.getSocket();
		Message message = new Message("CHANGE_MATTER", newSurname, newName, newAge, newUniver, newNumberOfGroup,
				idMatter);
		String response = null;
		try {
			socket.sendMessage(message);
			message = socket.receiveMessage();
			response = message.getAnswer();
		} catch (SocketStreamException e) {
			throw new DAOException(e);
		}
		return response;
	}

}
