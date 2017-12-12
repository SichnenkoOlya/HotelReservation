package by.bsuir.archive.service.impl;

import by.bsuir.archive.dao.DAOFactory;
import by.bsuir.archive.dao.DAOUser;
import by.bsuir.archive.dao.exception.DAOException;
import by.bsuir.archive.domain.User;
import by.bsuir.archive.service.UserService;
import by.bsuir.archive.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public int authenticate(String login, String password) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOUser userDAO = daoFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.authenticateUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user.getAccessRights();
	}

	@Override
	public boolean isAdmin(String login, String password) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOUser userDAO = daoFactory.getUserDAO();
		try {
			User user = userDAO.authenticateUser(login, password);
			if (user.getAccessRights() == 1) {
				return true;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return false;
	}

	@Override
	public void register(String login, String password) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOUser userDAO = daoFactory.getUserDAO();
		try {
			userDAO.registerUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	
}
