package clientvideo.dao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import clientvideo.POJO.User;
import clientvideo.datasource.DataSourceFactory;
import clientvideo.security.PBKDF2;

public class DaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			UserDao user = new UserDaoImpl(
					DataSourceFactory.getOracleDataSource().getConnection());

			int affected = user.insertUser("JAVA".toLowerCase(), "java");
			System.out.println(affected);

			User searchedUser = user.searchUser("TUSER".toLowerCase());
			if (searchedUser != null) {
				String userpass = searchedUser.getPassword();
System.out.println(userpass);
				try {
				System.out.println("validare? "+ PBKDF2.validatePassword("vvvv", userpass));	
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else{
				System.out.println("Username or pass wrong");
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
