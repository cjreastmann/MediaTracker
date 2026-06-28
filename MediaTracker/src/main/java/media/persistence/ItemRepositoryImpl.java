package media.persistence;

import java.util.List;
import java.util.ArrayList;

import java.sql.*;
import java.time.LocalDate;

import media.model.Item;
import media.model.Tag;

public class ItemRepositoryImpl implements ItemRepository{
	public void save(Item item) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "INSERT INTO item (title, category_id, status, date_added) VALUES (?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, item.getTitle());
		stmt.setInt(2, item.getCategoryId());
		stmt.setString(3, item.getStatus());
		stmt.setDate(4, Date.valueOf(item.getDate()));
		stmt.executeUpdate();
		
	}
	
	public Item findById(int itemId) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM item WHERE item_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, itemId);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			int itemID = rs.getInt("item_id");
			String title = rs.getString("title");
			int categoryId = rs.getInt("category_id");
			String status = rs.getString("status");
			LocalDate date = rs.getDate("date_added").toLocalDate();
			return new Item(itemID, title, categoryId, status, date);
		} else {
			return null;
		}
		
	}
	
	public List<Item> findAll() throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM item";
		PreparedStatement stmt = conn.prepareStatement(sql);
		List<Item> items = new ArrayList<Item>();
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			int itemID = rs.getInt("item_id");
			String title = rs.getString("title");
			int categoryId = rs.getInt("category_id");
			String status = rs.getString("status");
			LocalDate date = rs.getDate("date_added").toLocalDate();
			Item item = new Item(itemID, title, categoryId, status, date);
			items.add(item);
		}
		return items;
	}
	
	
	public void updateTitle(int itemId, String title)  throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "UPDATE item SET title = ? WHERE item_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, title);
		stmt.setInt(2, itemId);
		stmt.executeUpdate();
		
	}
	
	public void updateStatus(int itemId, String status)  throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "UPDATE item SET status = ? WHERE item_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, status);
		stmt.setInt(2, itemId);
		stmt.executeUpdate();
	}
	
	public void delete(int itemId)  throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "DELETE FROM item WHERE item_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, itemId);
		stmt.executeUpdate();
	}
	
	//In relation to categories
	
	public void updateCategoryId(int itemId, int categoryId) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "UPDATE item SET category_id = ? WHERE item_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		stmt.setInt(2, itemId);
		stmt.executeUpdate();
	}
	
	public List<Item> findAllInCategory(int categoryId)  throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM item WHERE category_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		List<Item> item = new ArrayList<Item>();
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			int itemID = rs.getInt("item_id");
			String title = rs.getString("title");
			int categoryID = rs.getInt("category_id");
			String status = rs.getString("status");
			LocalDate date = rs.getDate("date_added").toLocalDate();
			Item items = new Item(itemID, title, categoryID, status, date);
			item.add(items);
		}
		return item;
	}
	
	//Item in relation to Tag
	public void addTag(int itemId, int tagId) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "INSERT INTO item_tag (item_id, tag_id) VALUES (?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, itemId);
		stmt.setInt(2, tagId);
		stmt.executeUpdate();
	}
	
	public void removeTag(int itemId, int tagId) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "DELETE FROM item_tag WHERE item_id = ? AND tag_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, itemId);
		stmt.setInt(2, tagId);
		stmt.executeUpdate();
	}
	
	public List<Tag> getTagsForItem(int itemId) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT tag.tag_id, tag.name FROM tag JOIN item_tag ON tag.tag_id = item_tag.tag_id WHERE item_tag.item_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, itemId);
		List<Tag> itemTags = new ArrayList<Tag>();
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			int tagID = rs.getInt("tag_id");
			String tagName = rs.getString("name");
			Tag itemTagID = new Tag(tagID, tagName);
			itemTags.add(itemTagID);
		}
		return itemTags;
	}
}
