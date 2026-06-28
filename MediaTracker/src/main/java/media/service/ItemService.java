package media.service;

import java.sql.SQLException;
import java.util.List;

import media.model.Item;
import media.model.Tag;
import media.persistence.ItemRepository;
import media.persistence.TagRepository;
import media.persistence.CategoryRepository;


public class ItemService {
	private ItemRepository repo;
	private CategoryRepository categoryRepo;
	private TagRepository tagRepo;
	
	public ItemService(ItemRepository repo, CategoryRepository categoryRepo, TagRepository tagRepo) {
		this.repo = repo;
		this.categoryRepo = categoryRepo;
		this.tagRepo = tagRepo;
	}
	
	public void addItem(Item item) throws SQLException{
		if (item.getTitle() == null || item.getTitle().isBlank())  {
			throw new IllegalArgumentException("Title cannot be blank!");
		}
		if (!isValidStatus(item.getStatus())) {
			throw new IllegalArgumentException("Status must be: want / in_progress / done ");
		}
		if(categoryRepo.findById(item.getCategoryId()) == null) {
			throw new IllegalArgumentException("Category with id " + item.getCategoryId() + " does not exist.");
		}
		repo.save(item);
	}
	
	//getters
	public Item getItem(int itemId) throws SQLException {
		return repo.findById(itemId);
	}
	
	public List<Item> getAllItems() throws SQLException{
		return repo.findAll();
	}
	
	
	public void updateTitle(int itemId, String title) throws SQLException {
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("Title name cannot be blank!");
		}
		repo.updateTitle(itemId, title);
	}
	
	//helper
	private Boolean isValidStatus(String status) {
		if(status.equals("want") || status.equals("in_progress") || status.equals("done")) {
			return true;
		}else {
			return false;
		}
	}
	public void updateStatus(int itemId, String status) throws SQLException {
		if (!isValidStatus(status)) {
			throw new IllegalArgumentException("Status must be: want / in_progress / done ");
		}
		repo.updateStatus(itemId, status);
	}

	public void updateCategory(int itemId, int categoryId) throws SQLException {
		if(categoryRepo.findById(categoryId) == null) {
			throw new IllegalArgumentException("Category with id " + categoryId + " does not exist.");
		}
		repo.updateCategoryId(itemId, categoryId);
		
	}
	
	public List<Item> getItemsInCategory(int categoryId) throws SQLException{
		return repo.findAllInCategory(categoryId);
	}
	
	public void removeItem(int itemId) throws SQLException {
		if(repo.findById(itemId) == null) {
			throw new IllegalArgumentException("Item with id " + itemId + " does not exist.");
		}
		repo.delete(itemId);
	}
	
	//Item related to tags
	
	public void addTagtoItem(int itemId, int tagId) throws SQLException {
		if(tagRepo.findById(tagId) == null) {
			throw new IllegalArgumentException("Tag with id " + tagId + " does not exist.");
		}
		repo.addTag(itemId, tagId);
	}
	
	public void removeTagFromItem(int itemId, int tagId) throws SQLException {
		if(repo.findById(itemId) == null) {
			throw new IllegalArgumentException("Item with id " + itemId + " does not exist.");
		}
		if(tagRepo.findById(tagId) == null) {
			throw new IllegalArgumentException("Tag with id " + tagId + " does not exist.");
		}
		repo.removeTag(itemId, tagId);
	}
	
	public List<Tag> getTagsForItem(int itemId) throws SQLException {
		if(repo.findById(itemId) == null) {
			throw new IllegalArgumentException("Item with id " + itemId + " does not exist.");
		}
		return repo.getTagsForItem(itemId);
	}
	
}
