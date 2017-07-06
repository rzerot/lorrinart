package clientvideo.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DataSouceTest {
	public static void main(String[] args) {
	
		testDataSource("oracle");

	}

	private static void testDataSource(String dbType) {
		DataSource ds = null;

		if ("oracle".equals(dbType)) {
		
			ds = DataSourceFactory.getOracleDataSource();

		} else {
			System.out.println("invalid db type");
			return;
		}

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = DataSourceFactory.getOracleDataSource().getConnection().createStatement().executeQuery("Select * from CUSTOMER");

			while (rs.next()) {

				System.out.println(rs.getInt(1));
				System.out.println("Employee ID=" + rs.getInt("CUST_ID")
						+ ", Name=" + rs.getString("NUME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
