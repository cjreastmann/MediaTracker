package media.persistence;

import java.sql.*;

public class DatabaseConnection {
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/media_tracker", "root", "");
	}
}