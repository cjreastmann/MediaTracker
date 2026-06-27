package media.persistence;

import java.sql.*;
import media.model.*;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
	
	public void save(Category category) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		String sql = "INSERT INTO category (name) VALUES (?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, category.getCategoryName());
		stmt.executeUpdate();
		
	};
	public Category findById(int categoryId) {
		return null;
	};
	
	public List<Category> findAll(){
		return null;
	};
	
	
	public void updateCategoryName(int categoryId, String categoryName) {
	
	}
	public void delete(int categoryId) {
		
	}

}
