package media.persistence;

public class ConnectionTest {
	public static void main(String[] args) {
		
		try{
			DatabaseConnection.getConnection();
			System.out.println("Connect Successful");
		}catch(Exception e) {
			System.out.println("Connect Failed: " + e.getMessage());
		}
		

	}
}
