package by.bsuir.hotelReservation.service;

import by.bsuir.hotelReservation.domain.User;
import by.bsuir.hotelReservation.service.exception.ServiceException;

public interface UserService {

	User authenticate(String login, String password) throws ServiceException;

	User register(String login, String password) throws ServiceException;

}
