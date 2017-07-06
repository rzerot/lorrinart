package clientvideo.dao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clientvideo.POJO.User;
import clientvideo.datasource.DataSourceFactory;
import clientvideo.security.PBKDF2;

public class UserDaoImpl implements UserDao {
	public Connection connection = null;

	public UserDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public int insertUser(String name, String auth) {
		// TODO Auto-generated method stub
		// String saltToString = null;
		String password = null;
		try {
			if (connection == null) {
				connection = DataSourceFactory.getOracleDataSource()
						.getConnection();
			}
			Statement statement = connection.createStatement();
			try {
				byte[] salt = PBKDF2.getSalt();
				// saltToString = PBKDF2.saltToString(salt);
				password = PBKDF2.generatePasswordHash(auth, salt);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String sql = "Insert into Users(USERNAME, USER_PASSWORD) values('"
					+ name + "','" + password + "')";
			int affectedRows = statement.executeUpdate(sql);
			return affectedRows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteUser(int user_id) {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public int deleteUser(String username) {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public User searchUser(int user_id) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		if (connection == null) {
			try {
				connection = DataSourceFactory.getOracleDataSource()
						.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String sql = "select * FROM USERS where USER_ID=" + user_id;
		try {
			rs = connection.prepareStatement(sql).executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("USER_ID"));
				user.setName("USERNAME");
				user.setPassword("USER_PASSWORD");
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User searchUser(String name) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		if (connection == null) {
			try {
				connection = DataSourceFactory.getOracleDataSource()
						.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String sql = "select * from  Users where USERNAME='" + name + "'";
		try {
			rs = connection.prepareStatement(sql).executeQuery();
			// System.out.println(rs.findColumn("name"));
			if (rs.next()) {
				User user = new User();
				// user.setUser_id(rs.getInt("USER_ID"));
				user.setName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("USER_PASSWORD"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
