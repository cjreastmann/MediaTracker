package media.service;

import java.sql.SQLException;
import java.util.List;

import media.model.Category;
import media.persistence.CategoryRepository;

public class CategoryService {
	private CategoryRepository repo;
	
	public CategoryService(CategoryRepository repo) {
		this.repo = repo;
	}
	
	public void addCategory(Category category) throws SQLException {
		if (category.getCategoryName() == null || category.getCategoryName().isBlank()) {
		    throw new IllegalArgumentException("Category name cannot be blank!");
		}
		repo.save(category);
	}
	
	public void updateCategory(int categoryId, String categoryName) throws SQLException{
		if (categoryName == null || categoryName.isBlank()) {
		    throw new IllegalArgumentException("Category name cannot be blank!");
		}
		repo.updateName(categoryId, categoryName);
	}
	
	public Category getCategory(int categoryId) throws SQLException{
		return repo.findById(categoryId);
	}
	
	public List<Category> getAllCategories() throws SQLException{
		return repo.findAll();
	}
	
	public void removeCategory(int categoryId)throws SQLException{
		repo.delete(categoryId);
	}
	
}
