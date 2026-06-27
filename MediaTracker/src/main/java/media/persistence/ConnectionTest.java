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
		
		System.out.println(item.findAllInCategory(3));
		
		
		item.updateTitle(10, "Regular Show");
		
		System.out.println(item.findById(10));
		System.out.println(item.findAll());

		System.out.println(repo.findById(2));
		System.out.println(repo.findAll());
		
		System.out.println(tag.findById(2));
		System.out.println(tag.findAll());
		
		
	}
}
