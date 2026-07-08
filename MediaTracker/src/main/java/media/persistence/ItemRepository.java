package media.persistence;

import media.model.*;

import java.sql.SQLException;
import java.util.List;

public interface ItemRepository {
	//methods for Items with relation to categories
	public void save(Item item) throws SQLException;
	List<Item> getItemsByStatus(String status) throws SQLException;
	Item findById(int itemId) throws SQLException;
	List<Item> findAll() throws SQLException;
	public void updateTitle(int itemId, String title) throws SQLException;
	public void updateStatus(int itemId, String status)throws SQLException;
	public void updateCategoryId(int itemId, int categoryId) throws SQLException;
	List<Item> findAllInCategory(int categoryId) throws SQLException;
	public void delete(int itemId) throws SQLException;
	
	//methods for Items with relation to Tags
	void addTag(int itemId, int tagId) throws SQLException;
	void removeTag(int itemId, int tagId) throws SQLException;
	List<Tag> getTagsForItem(int itemId) throws SQLException;
}
