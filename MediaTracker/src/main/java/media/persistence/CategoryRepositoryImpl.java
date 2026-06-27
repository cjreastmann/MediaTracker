package media.persistence;

import java.sql.*;
import media.model.*;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
	
	public void save(Category category) throws SQLException {
		Connection conn = DatabaseConnection.getConnection(); //opens connection
		String sql = "INSERT INTO category (name) VALUES (?)";
		PreparedStatement stmt = conn.prepareStatement(sql); // prepares sql using connection
		stmt.setString(1, category.getCategoryName()); //fills in ? placeholder
		stmt.executeUpdate(); //runs the insert
		
	};
	
	public Category findById(int categoryId) throws SQLException {
		Connection conn = DatabaseConnection.getConnection(); //opens connection
		String sql = "SELECT * FROM category WHERE category_id = ?"; //select the row matching the given category_id
		PreparedStatement stmt = conn.prepareStatement(sql); // prepares sql using connection
		stmt.setInt(1, categoryId); //fills in ? placeholder
		ResultSet rs = stmt.executeQuery(); //returns a ResultSet of rows
		
		// .next() moves to the first row and returns true if one exists
		if(rs.next()) { 
			int id = rs.getInt("category_id");
			String name = rs.getString("name");
			return new Category(id, name);
		}else { 
			return null;  // No matching row found
		}
	};
	
	public List<Category> findAll() throws SQLException{
		Connection conn = DatabaseConnection.getConnection(); //connect
		String sql = "SELECT * FROM category"; //select all from category
		PreparedStatement stmt = conn.prepareStatement(sql); 
		ResultSet rs = stmt.executeQuery();	
		List<Category> categories = new ArrayList<Category>(); //empty array for category to go into
		while(rs.next()) {
			int id = rs.getInt("category_id");
			String name = rs.getString("name");
			Category category = new Category(id, name); //creates Category object for categories ArrayList
			categories.add(category);
		}
		return categories;
	};
	
	
	public void updateName(int categoryId, String categoryName) throws SQLException {
		Connection conn = DatabaseConnection.getConnection(); //connect
		String sql = "UPDATE category SET name = ? WHERE category_id = ?"; //update category, set it to name, where the category id = 
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, categoryName); //first filler
		stmt.setInt(2, categoryId); //second filler
		stmt.executeUpdate(); //runs the update
	
	}
	public void delete(int categoryId) throws SQLException {
		Connection conn = DatabaseConnection.getConnection(); //opens connection
		String sql = "DELETE FROM category WHERE category_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId); //for ? filler
		stmt.executeUpdate();
		
	}

}
