package media.model;

import java.time.LocalDate;

public class Item {
	private int itemId;
	private String title;
	private int categoryId;
	private String status;
	private LocalDate dateAdded;
	
	//knowing itemId from database, existing data
	public Item(int itemId, String title, int categoryId, String status, LocalDate dateAdded) {
		this.itemId = itemId;
		this.title = title;
		this.categoryId =  categoryId;
		this.status = status;
		this.dateAdded = dateAdded;
	}
	
	//unknown itemId, creating data
	public Item(String title, int categoryId, String status) {
		this(0, title, categoryId, status, LocalDate.now());
	}
	
	
	//getters
	public int getItemId() {
		return itemId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public LocalDate getDate() {
		return dateAdded;
	}
	
	//setters
	public void setStatus(String s) {
		status = s;
	}
	
	public void setTitle(String t) {
		title = t;
	}
	
	public void setCategoryId(int n) {
		categoryId = n;
	}

}
