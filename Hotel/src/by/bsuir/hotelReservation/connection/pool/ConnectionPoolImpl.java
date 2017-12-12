package by.bsuir.hotelReservation.connection.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.bsuir.hotelReservation.connection.pool.exception.ConnectionPoolException;

public final class ConnectionPoolImpl implements ConnectionPool {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/hotel";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";
	private static final int POOL_SIZE = 2;
	private static final String MESSAGE_SQL_EXCEPTION_CONNECTION_POOL = "SQLException in ConnectionPool";
	private static final String MESSAGE_ERROR_CLOSING_CONNECTION = "Error closing the connection.";
	private static final String MESSAGE_ERROR_FIND_DATABASE_DRIVER = "Can't find database driver class";
	private static final String MESSAGE_ERROR_CONNECTION_DATA_SOURCE = "Error connecting to the data source.";
	private static final String MESSAGE_CONNECTION_NOT_RETURN_POOL = "Connection isn't return to the pool.";
	private static final String MESSAGE_RESULTSET_IS_NOT_CLOSED = "ResultSet isn't closed.";
	private static final String MESSAGE_STATEMENT_IS_NOT_CLOSED = "Statement isn't closed.";
	private static final String MESSAGE_ERROR_REMOVE_DATA_SOURCE = "Error remove to the data source.";

	private BlockingQueue<Connection> availableConnections;
	private BlockingQueue<Connection> usedConnections;
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	ConnectionPoolImpl() {
		this.driverName = DRIVER_NAME;
		this.url = URL;
		this.user = USER;
		this.password = PASSWORD;
		this.poolSize = POOL_SIZE;
	}

	public void initPoolData() throws ConnectionPoolException {
		try {
			Class.forName(driverName);
			usedConnections = new ArrayBlockingQueue<Connection>(poolSize);
			availableConnections = new ArrayBlockingQueue<Connection>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				availableConnections.add(connection);
			}
		} catch (SQLException e) {

			throw new ConnectionPoolException(MESSAGE_SQL_EXCEPTION_CONNECTION_POOL, e);

		} catch (ClassNotFoundException e) {

			throw new ConnectionPoolException(MESSAGE_ERROR_FIND_DATABASE_DRIVER, e);
		}
	}

	public void dispose() throws ConnectionPoolException {
		try {
			clearConnectionQueue();
		} catch (SQLException e) {
			throw new ConnectionPoolException(MESSAGE_ERROR_CLOSING_CONNECTION, e);
		}
	}

	private void clearConnectionQueue() throws SQLException {
		closeConnectionsQueue(usedConnections);
		closeConnectionsQueue(availableConnections);
	}

	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = availableConnections.take();
			usedConnections.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException(MESSAGE_ERROR_CONNECTION_DATA_SOURCE, e);
		}
		return connection;
	}

	public void removeConnection() throws ConnectionPoolException {
		Connection connection;
		try {
			connection = usedConnections.take();
			availableConnections.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException(MESSAGE_ERROR_REMOVE_DATA_SOURCE, e);
		}
	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException {
		try {
			con.close();
		} catch (SQLException e) {
			throw new ConnectionPoolException(MESSAGE_CONNECTION_NOT_RETURN_POOL, e);
		}
		try {
			rs.close();
		} catch (SQLException e) {
			throw new ConnectionPoolException(MESSAGE_RESULTSET_IS_NOT_CLOSED, e);
		}
		try {
			st.close();
		} catch (SQLException e) {
			throw new ConnectionPoolException(MESSAGE_STATEMENT_IS_NOT_CLOSED, e);
		}
	}

	public void closeConnection(Connection con, Statement st) throws ConnectionPoolException {
		try {
			con.close();
		} catch (SQLException e) {
			throw new ConnectionPoolException(MESSAGE_CONNECTION_NOT_RETURN_POOL, e);
		}
		try {
			st.close();
		} catch (SQLException e) {
			throw new ConnectionPoolException(MESSAGE_STATEMENT_IS_NOT_CLOSED, e);

		}
	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
		}
	}
}
