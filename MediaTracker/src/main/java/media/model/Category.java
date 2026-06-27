package media.model;

public class Category {
	private int categoryId;
	private String categoryName;
	
	public Category(int categoryId, String name) {
		this.categoryId = categoryId;
		this.categoryName = name;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	//maybe add setter for name later
	

}
