package media.persistence;

import java.sql.*;
import media.model.*;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    public void save(Category category) throws SQLException {
        String sql = "INSERT INTO category (name) VALUES (?)";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, category.getCategoryName());
            stmt.executeUpdate();
        }
    }

    public Category findById(int categoryId) throws SQLException {
        String sql = "SELECT * FROM category WHERE category_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, categoryId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("category_id");
                    String name = rs.getString("name");
                    return new Category(id, name);
                }
            }
        }

        return null;
    }

    public List<Category> findAll() throws SQLException {
        String sql = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                int id = rs.getInt("category_id");
                String name = rs.getString("name");

                categories.add(new Category(id, name));
            }
        }

        return categories;
    }

    public void updateName(int categoryId, String categoryName) throws SQLException {
        String sql = "UPDATE category SET name = ? WHERE category_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, categoryName);
            stmt.setInt(2, categoryId);

            stmt.executeUpdate();
        }
    }

    public void delete(int categoryId) throws SQLException {
        String sql = "DELETE FROM category WHERE category_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, categoryId);

            stmt.executeUpdate();
        }
    }
}
