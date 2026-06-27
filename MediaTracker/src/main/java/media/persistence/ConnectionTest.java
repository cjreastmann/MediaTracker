package media.persistence;

import media.model.*;

public class ConnectionTest {
	public static void main(String[] args) {
		
		try{
			DatabaseConnection.getConnection();
			System.out.println("Connect Successful");
		}catch(Exception e) {
			System.out.println("Connect Failed: " + e.getMessage());
		}
		
		CategoryRepositoryImpl repo = new CategoryRepositoryImpl();
		try {
		repo.save(new Category("TestCategory"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
