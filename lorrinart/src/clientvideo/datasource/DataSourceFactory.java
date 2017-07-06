package clientvideo.datasource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class DataSourceFactory {
	Connection connection = null;

	public static DataSource getOracleDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
		OracleDataSource oracleDS = null;
		try {
			System.out.println(new File(".").getAbsolutePath());
			fis = new FileInputStream("db.properties");
			props.load(fis);
			oracleDS = new OracleDataSource();
			oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
			oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
			oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oracleDS;
	}

//	public static Connection createConnection(DataSource dataSource)
//			throws SQLException {
//		return dataSource.getConnection();
//
//	}
	

}
