package media.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import media.model.Tag;

public class TagRepositoryImpl implements TagRepository {

    public void save(Tag tag) throws SQLException {
        String sql = "INSERT INTO tag (name) VALUES (?)";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, tag.getTagName());
            stmt.executeUpdate();
        }
    }

    public Tag findById(int tagId) throws SQLException {
        String sql = "SELECT * FROM tag WHERE tag_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, tagId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("tag_id");
                    String name = rs.getString("name");

                    return new Tag(id, name);
                }
            }
        }

        return null;
    }

    public List<Tag> findAll() throws SQLException {
        String sql = "SELECT * FROM tag";
        List<Tag> tags = new ArrayList<>();

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                int id = rs.getInt("tag_id");
                String name = rs.getString("name");

                tags.add(new Tag(id, name));
            }
        }

        return tags;
    }

    public void updateName(int tagId, String tagName) throws SQLException {
        String sql = "UPDATE tag SET name = ? WHERE tag_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, tagName);
            stmt.setInt(2, tagId);

            stmt.executeUpdate();
        }
    }

    public void delete(int tagId) throws SQLException {
        String sql = "DELETE FROM tag WHERE tag_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, tagId);

            stmt.executeUpdate();
        }
    }
}
