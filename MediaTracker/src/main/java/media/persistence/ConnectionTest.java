package media.persistence;

import java.sql.SQLException;

import media.model.*;

public class ConnectionTest {
	public static void main(String[] args) throws SQLException {
		//throws SQLException only for tests
	
		DatabaseConnection.getConnection();
		System.out.println("Connect Successful");
	
		CategoryRepositoryImpl repo = new CategoryRepositoryImpl();
		TagRepositoryImpl tag = new TagRepositoryImpl();
		ItemRepositoryImpl item = new ItemRepositoryImpl();
		

		System.out.println(repo.findById(4));
		System.out.println(repo.findAll());
			
		
	}
}
