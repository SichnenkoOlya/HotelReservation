package by.bsuir.hotelReservation.dao;

import by.bsuir.hotelReservation.domain.User;
import by.bsuir.hotelReservation.dao.exception.DAOException;

public interface DAOUser {
	User registration(String login, String password) throws DAOException;

	User authorization(String login, String password) throws DAOException;
}
