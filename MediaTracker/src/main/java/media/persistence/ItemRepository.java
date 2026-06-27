package media.persistence;

import media.model.*;
import java.util.List;

public interface ItemRepository {
	public void save(Item item);
	Item findById(int itemId);
	List<Item> findAll();
	public void updateTitle(int itemId, String title);
	public void updateStatus(int itemId, String status);
	public void updateCategoryId(int itemId, int categoryId);
	List<Item> findAllInCategory(int categoryId);
	public void delete(int itemId);
	


}
