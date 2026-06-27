package media.model;

public class Category {
	private int categoryId;
	private String name;
	
	public Category(int categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	//maybe add setter for name later
	

}
