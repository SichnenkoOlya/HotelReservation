package by.bsuir.hotelReservation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.bsuir.hotelReservation.connection.pool.ConnectionPool;
import by.bsuir.hotelReservation.connection.pool.ConnectionPoolFactory;
import by.bsuir.hotelReservation.connection.pool.exception.ConnectionPoolException;
import by.bsuir.hotelReservation.dao.DAOUser;
import by.bsuir.hotelReservation.dao.exception.DAOException;
import by.bsuir.hotelReservation.domain.User;

public class DAOUserImpl implements DAOUser {
	private static final String USER_SELECT_LOGIN_PASSWORD = "SELECT * FROM users WHERE name=? AND password=?";
	private static final String USER_SELECT_LOGIN = "SELECT * FROM USERS WHERE name=?";
	private static final String USER_ADD = "INSERT INTO USERS (name,PASSWORD,ACCESSRIGHTS) VALUES (?,?,?)";
	private static final String MESSAGE_ERROR_CONNECTION_POOL = "Error at connection pool.";
	private static final String MESSAGE_ERROR_SQL = "Error at sql.";
	private static final String MESSAGE_ERROR_REMOVE_CONNECTION = "Error at remove connection.";
	private static final int FIRST = 1;
	private static final int SECOND = 2;
	private static final int THIRD = 3;
	private static final int FOURTH = 4;

	@Override
	public User authorization(String login, String password) throws DAOException {
		Connection connection = null;
		ResultSet resultSet = null;
		User user = null;

		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ObjectCPFactory.getConnectionPool();
		} catch (ConnectionPoolException e1) {
			throw new DAOException(MESSAGE_ERROR_CONNECTION_POOL);
		}
		try {
			connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_LOGIN_PASSWORD);
			preparedStatement.setString(FIRST, login);
			preparedStatement.setString(SECOND, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String loginBD = resultSet.getString(SECOND);
				String passwordBD = resultSet.getString(THIRD);
				String accessRights = resultSet.getString(FOURTH);
				user = new User();
				user.setName(loginBD);
				user.setPassword(Integer.parseInt(passwordBD));
				user.setAccessRights(Integer.parseInt(accessRights));

			}

		} catch (ConnectionPoolException e) {
			throw new DAOException(MESSAGE_ERROR_CONNECTION_POOL);
		} catch (SQLException e) {
			throw new DAOException(MESSAGE_ERROR_SQL);
		} finally {
			try {
				connectionPool.removeConnection();
			} catch (ConnectionPoolException e) {
				throw new DAOException(MESSAGE_ERROR_REMOVE_CONNECTION);
			}
		}
		return user;
	}

	@Override
	public User registration(String login, String password) throws DAOException {
		Connection connection = null;
		ResultSet resultSet = null;
		User user = null;

		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ObjectCPFactory.getConnectionPool();
		} catch (ConnectionPoolException e1) {
			throw new DAOException(MESSAGE_ERROR_CONNECTION_POOL);
		}
		try {
			connection = connectionPool.takeConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_LOGIN);
			preparedStatement.setString(FIRST, login);
			resultSet = preparedStatement.executeQuery();

			int i = 0;
			while (resultSet.next()) {
				i = resultSet.getInt("id");
			}
			if (i == 0) {
				preparedStatement = connection.prepareStatement(USER_ADD);

				preparedStatement.setString(FIRST, login);
				preparedStatement.setString(SECOND, password);
				preparedStatement.setInt(THIRD, 0);
				preparedStatement.executeUpdate();

				preparedStatement = connection.prepareStatement(USER_SELECT_LOGIN_PASSWORD);
				preparedStatement.setString(FIRST, login);
				preparedStatement.setString(SECOND, password);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					String nameBD = resultSet.getString(SECOND);
					String passwordBD = resultSet.getString(THIRD);
					String accessRights = resultSet.getString(FOURTH);
					user = new User();
					user.setName(nameBD);
					user.setPassword(Integer.parseInt(passwordBD));
					user.setAccessRights(Integer.parseInt(accessRights));
				}
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException(MESSAGE_ERROR_CONNECTION_POOL);
		} catch (SQLException e) {
			throw new DAOException(MESSAGE_ERROR_SQL);
		} finally {
			try {
				connectionPool.removeConnection();
			} catch (ConnectionPoolException e) {
				throw new DAOException(MESSAGE_ERROR_REMOVE_CONNECTION);
			}
		}
		return user;
	}
}