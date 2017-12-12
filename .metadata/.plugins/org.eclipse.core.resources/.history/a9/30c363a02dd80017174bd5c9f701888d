package by.bsuir.archive.dao.impl;

import java.util.ArrayList;
import by.bsuir.archive.dao.DAOUser;
import by.bsuir.archive.dao.exception.DAOException;
import by.bsuir.archive.domain.User;
import by.bsuir.archive.serialization.Serialization;
import by.bsuir.archive.serialization.exception.SerializationException;

public class DAOUserImpl implements DAOUser {

	protected static ArrayList<User> listOfUser = new ArrayList<User>();
	protected static String fileDBUser = "DB_FILE_USER.txt";

	@Override
	public User registerUser(String login, String password) throws DAOException {

		try {
			listOfUser = Serialization.deserialize(fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}

		boolean isInList = false;
		for (User element : listOfUser) {
			if (element.getName().equals(login)) {
				isInList = true;
			}
		}
		if (isInList) {
			throw new DAOException("Такой логин занят!");
		}
		User user = new User();
		user.setName(login);
		int hashOfPassword = (login + password).hashCode();
		user.setPassword(hashOfPassword);
		listOfUser.add(user);
		try {
			Serialization.serialize(listOfUser, fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}
		return user;
	}

	@Override
	public User authenticateUser(String login, String password) throws DAOException {
		try {
			listOfUser = Serialization.deserialize(fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}
		boolean isInList = false;
		User user = new User();
		for (User element : listOfUser) {
			if (element.getName().equals(login) && element.getPassword() == (login + password).hashCode()) {
				isInList = true;
				user = element;
			}
		}
		if (!isInList) {
			throw new DAOException("Некорректные данные!");
		}

		return user;
	}

}
