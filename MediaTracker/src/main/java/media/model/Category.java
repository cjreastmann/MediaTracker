package media.model;

public class Category {
	private int categoryId;
	private String categoryName;
	
	//known category, has catID and name
	public Category(int categoryId, String name) {
		this.categoryId = categoryId;
		this.categoryName = name;
	}
	
	//new category, unknown catID
	public Category(String name) {
		this(0, name);
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	
	//findById readable
	@Override
	public String toString() {
	    return "Category: id = " + categoryId + ", name = " + categoryName + "";
	}
	
	//maybe add setter for name later
	

}
