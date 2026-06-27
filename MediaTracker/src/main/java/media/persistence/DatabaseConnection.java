package media.persistence;

import java.sql.*;

public class DatabaseConnection {
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "root", "root");
	}
}

//make sure container has volume in-case of removable