package media.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import media.model.Tag;

public class TagRepositoryImpl implements TagRepository{
	public void save(Tag tag) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		String sql = "INSERT INTO tag (name) VALUES (?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, tag.getTagName());
		stmt.executeUpdate();
		
	}
	
	public Tag findById(int tagId) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM tag WHERE tag_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, tagId);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			int id = rs.getInt("tag_id");
			String name = rs.getString("name");
			return new Tag(id, name);
		} else {
			return null;
		}
		
	}
	
	public List<Tag> findAll() throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM tag";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Tag> tags = new ArrayList<Tag>();
		while(rs.next()) {
			int id = rs.getInt("tag_id");
			String name = rs.getString("name");
			Tag tag = new Tag(id, name);
			tags.add(tag);
		}
		return tags;
		
	}
	
	public void updateName(int tagId, String tagName) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		String sql = "UPDATE tag SET name = ? WHERE tag_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, tagName);
		stmt.setInt(2, tagId);
		stmt.executeUpdate();
		
	}
	
	public void delete(int tagId) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		String sql = "DELETE FROM tag WHERE tag_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, tagId);
		stmt.executeUpdate();
	}
	
}
