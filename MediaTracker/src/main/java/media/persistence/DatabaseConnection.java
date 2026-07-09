package media.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Properties props = new Properties();
            InputStream input = DatabaseConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties");
            props.load(input);
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            return DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            throw new SQLException("Failed to load database properties", e);
        }
    }
}
//make sure container has volume in-case of removable