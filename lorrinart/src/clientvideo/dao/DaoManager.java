package clientvideo.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager {
	protected Connection connection = null;
	protected UserDao userDao = null;

	public DaoManager(Connection connection) {
		this.connection = connection;

	}

	public UserDao getUserDao() {
		if (this.userDao == null) {
			this.userDao = new UserDaoImpl(this.connection);
		}
		return this.userDao;
	}

	public Object executeAndClose(DaoCommand daoCommand) {
		try {
			return daoCommand.execute(this);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
