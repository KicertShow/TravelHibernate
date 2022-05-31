package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public final class DBConnection {
	
	private static final String USER = "sa";
	private static final String PASSWORD = "manager";
	private static final String DB_NAME = "travel_web";
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
	
	private static Connection conn;
	
	// 以DataSource取得資料庫連線物件
	public static Connection getConnectionObject() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("localhost");
        ds.setPortNumber(1433);
        ds.setUser(USER);
        ds.setPassword(PASSWORD);
        ds.setDatabaseName(DB_NAME);
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
		
	
	// 以DriverManager取得資料庫連線
	public static Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	
	// 關閉Connection
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
