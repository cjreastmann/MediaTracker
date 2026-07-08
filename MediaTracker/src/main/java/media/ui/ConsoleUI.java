package media.ui;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import media.model.Category;
import media.model.Tag;
import media.model.Item;
import media.service.CategoryService;
import media.service.ItemService;
import media.service.RatingService;
import media.service.TagService;
import media.persistence.CategoryRepository;
import media.persistence.CategoryRepositoryImpl;
import media.persistence.ItemRepository;
import media.persistence.ItemRepositoryImpl;
import media.persistence.RatingRepository;
import media.persistence.RatingRepositoryImpl;
import media.persistence.TagRepository;
import media.persistence.TagRepositoryImpl;

public class ConsoleUI {
	private CategoryService serv;
	private ItemService itemServ;
	private RatingService ratingServ;
	private TagService tagServ;
	private Scanner scanner;
	
	public ConsoleUI(CategoryService serv, ItemService itemServ, RatingService ratingServ, TagService tagServ, Scanner scanner) {
		this.serv = serv;
		this.itemServ = itemServ;
		this.ratingServ = ratingServ;
		this.tagServ = tagServ;
		this.scanner = scanner;
	}
	
	public void start() throws SQLException {
		boolean running = true;
		while(running) {
			System.out.println("Main Menu");
			System.out.println("1. Manage Catgeories");
			System.out.println("2. Manage Items");
			System.out.println("3. Manage Tags");
			System.out.println("4. Manage Ratings");
			System.out.println("5. Exit");
			System.out.println();
			System.out.println("Enter an Option: ");
		    int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
			case 1: 
				categoryStart();
				break;
			case 2: 
				itemStart();
				break;
			case 3: 
				tagStart();
				break;
			case 4: 
				break;
			case 5: 
				System.out.println("Exiting...");
				running = false;
				break;
			default:
				System.out.println("Must enter an option 1-5");
			
			}
		}
	}
	
	
	//Category Options List
	public void categoryStart() throws SQLException { 
		int categoryChoice = 0;
		while(categoryChoice != 6) {
		System.out.println("Manage Catgeories: ");
		System.out.println("1. Add Category");
		System.out.println("2. Update Category");
		System.out.println("3. View Items in Category");
		System.out.println("4. View All Categories");
		System.out.println("5. Delete Category");
		System.out.println("6. Back to Main Menu");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch(choice) {
			case 1: {
				System.out.println("Enter a Category Name");
				String categoryName = scanner.nextLine();
				Category category = new Category(categoryName);
				serv.addCategory(category);
				break;
			}
			case 2: {
				//Ask and show user list of possible categories
				List<Category> categories = serv.getAllCategories();
				for(Category c : categories) {
					System.out.println(c);
				}
				System.out.println("Enter a Category Name to Replace");
				//Check for name in Category List
				String enteredCat = scanner.nextLine();
				Category foundCategoryName = findCategoryByName(enteredCat, categories);
				if(foundCategoryName == null) {
					System.out.println("Category not found");
				} else {
					System.out.println("Enter a new name for the Category:");
					String newCategory = scanner.nextLine();
					int categoryId = foundCategoryName.getCategoryId();
					serv.updateCategory(categoryId, newCategory);
				}
				break;
			}
			case 3: {
				List<Category> categories = serv.getAllCategories();
				for(Category c : categories) {
					System.out.println(c);
				}
				System.out.println("Enter a Category Name from above: ");
				String enteredCat = scanner.nextLine();
				Category foundCategoryName = findCategoryByName(enteredCat, categories);
				if(foundCategoryName == null) {
					System.out.println("Category not found");
				} else {
					int categoryId = foundCategoryName.getCategoryId();
					System.out.println("Items in Category " + enteredCat);
					System.out.println(itemServ.getItemsInCategory(categoryId));
				}
				break;
			}
			case 4: {
				System.out.println("List of each Category:");
				List<Category> categories = serv.getAllCategories();
				for(Category c : categories) {
					System.out.println(c);
				}
				break;
			}
			case 5: {
				//Delete Category by Name
				List<Category> categories = serv.getAllCategories();
				for(Category c : categories) {
					System.out.println(c);
				}
				System.out.println("Enter a Category Name to Delete: ");
				String enteredCat = scanner.nextLine();
				Category foundCategoryName = findCategoryByName(enteredCat, categories);
				if(foundCategoryName == null) {
					System.out.println("Category not found");
				} else {
					int categoryId = foundCategoryName.getCategoryId();
					serv.removeCategory(categoryId);
				}
				
				break;
			}
			case 6: {
				System.out.println("Exiting...");
				categoryChoice = 6;
				break;
			}
			default:{
			System.out.println("Must enter an option 1-6");
			}
			}
		}
	}
	
	
	//Helper Method for Update Category, allows user to enter name to find Category instead of ID
	private Category findCategoryByName(String name, List<Category> categories){
		Category found = null;
		for(Category c : categories) {
			if(c.getCategoryName().equalsIgnoreCase(name)){
				found = c;
				break;
			}
		}
		return found;
	}
	
	public void tagStart() throws SQLException { 
		int tagChoice = 0;
		while(tagChoice != 5) {
		System.out.println("Manage Tags: ");
		System.out.println("1. Add Tag");
		System.out.println("2. Update Tags");
		System.out.println("3. View all Tags");
		System.out.println("4. Delete Tag");
		System.out.println("5. Back to Main Menu");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch(choice) {
			case 1: {
				//Add Tag
				System.out.println("Enter a New Tag Name: ");
				String tagName = scanner.nextLine();
				Tag newTag = new Tag(tagName);
				tagServ.addTag(newTag);
				break;
			}
			case 2: {
				//Update Tags
				List<Tag> tags = tagServ.getAllTags();
				for(Tag t : tags) {
				    System.out.println(t);
				}
				System.out.println("Enter a Tag Name from above: ");
				String enteredTag = scanner.nextLine();
				Tag foundTagName = findTagByName(enteredTag, tags);
				if(foundTagName == null) {
					System.out.println("Tag not found");
				} else {
					System.out.print("Enter a new Tag name: ");
					String newTag = scanner.nextLine();
					int tagId = foundTagName.getTagId();
					tagServ.updateTag(tagId,newTag);
				}
				break;
			}
			case 3: {
				//View all Tags
				System.out.println("List of each Tag: ");
				List<Tag> tags = tagServ.getAllTags();
				for(Tag t : tags) {
					System.out.println(t);
				}
			
				break;
			}
			case 4: {
				//Delete Tag
				List<Tag> tags = tagServ.getAllTags();
				for(Tag t : tags) {
				    System.out.println(t);
				}
				System.out.println("Enter a Tag Name from above to Delete: ");
				String enteredTag = scanner.nextLine();
				Tag foundTagName = findTagByName(enteredTag, tags);
				if(foundTagName == null) {
					System.out.println("Tag not found");
				} else {
					int TagId = foundTagName.getTagId();
					tagServ.removeTag(TagId);
				}
				
				break;
			}
			case 5: {
				System.out.println("Exiting...");
				tagChoice = 5;
				break;
			}
			default:{
			System.out.println("Must enter an option 1-5");
			}
			}
		}
	}
	
	private Tag findTagByName(String name, List<Tag> tags){
		Tag found = null;
		for(Tag t : tags) {
			if(t.getTagName().equalsIgnoreCase(name)){
				found = t;
				break;
			}
		}
		return found;
	}
	
	public void itemStart() throws SQLException { 
		int itemChoice = 0;
		while(itemChoice != 8) {
		System.out.println("Manage Items: ");
		System.out.println("1. Add Item");
		System.out.println("2. View Item by Name");
		System.out.println("3. View All Items");
		System.out.println("4. View Items by Status");
		System.out.println("5. Edit Item");
		System.out.println("6. Manage Tags on Item");
		System.out.println("7. Delete Item");
		System.out.println("8. Back");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch(choice) {
			case 1: {
				//Add Item
				System.out.println("Enter a New Item Title: ");
				String itemName = scanner.nextLine();
				List<Category> categories = serv.getAllCategories();
				for(Category c : categories) {
					System.out.println(c);
				}
				System.out.println("Enter a Category to Add Item to: ");
				String catName = scanner.nextLine();
				System.out.println("Enter New Item Status (done/want/in_progress): ");
				String itemStatus = scanner.nextLine();
				Category foundCategoryName = findCategoryByName(catName, categories);
				if(foundCategoryName == null) {
					System.out.println("Category not found");
				} else {
					int categoryId = foundCategoryName.getCategoryId();
					Item newItem = new Item(itemName, categoryId, itemStatus);
					itemServ.addItem(newItem);
				}
				break;
			}
			case 2: {
				//View Item by Name
				List<Item> items = itemServ.getAllItems();
				for(Item i : items) {
					System.out.println(i);
				}
				System.out.println("Enter an Item Title: ");
				String itemName = scanner.nextLine();
				Item foundItem = findItemByName(itemName, items);
				if(foundItem == null) {
					System.out.println("Item not found");
				} else {
					System.out.println(foundItem);
				}
				break;
			}
			case 3: {
				//View all Items
				System.out.println("List of each Item: ");
				List<Item> items = itemServ.getAllItems();
				for(Item i : items) {
					System.out.println(i);
				}
				break;
			}
			case 4: {
				//view item by status
				System.out.println("Enter a Status (done/want/in_progess): ");
				String itemStatus = scanner.nextLine();
				List<Item> foundItem = itemServ.getItemsByStatus(itemStatus);
				for(Item i : foundItem) {
					System.out.println(i.toString());
				}
				break;
			}
			case 5: {
				//edit item
				editItemStart();
				break;
			}
			case 6: {
				//manage tags on item
				manageItemTagsStart();
				break;
			}
			case 7: {
				//delete item
				List<Item> items = itemServ.getAllItems();
				for(Item i : items) {
					System.out.println(i);
				}
				System.out.println("Enter an Item to Delete");
				String itemName = scanner.nextLine();
				Item foundItem = findItemByName(itemName, items);
				if(foundItem == null) {
					System.out.println("Item not found");
				} else {
					itemServ.removeItem(foundItem.getItemId());
				}
				break;
			}
			case 8: {
				//exit
				System.out.println("Exiting...");
				itemChoice = 8;
				break;
			}
			default:{
			System.out.println("Must enter an option 1-8");
			}
			}
		}
	}
	
	public void editItemStart() throws SQLException{
		int itemEditChoice = 0;
		while(itemEditChoice != 4) {
		System.out.println("Edit Items: ");
		System.out.println("1. Update Title");
		System.out.println("2. Update Status");
		System.out.println("3. Update Category");
		System.out.println("4. Back");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch(choice) {
			case 1:{
				//Update Item Title
				List<Item> items = itemServ.getAllItems();
				for(Item i : items) {
					System.out.println(i);
				}
				System.out.println("Enter a Title to Update: ");
				String itemTitle = scanner.nextLine();
				Item itemFound = findItemByName(itemTitle, items);
				if(itemFound == null) {
					System.out.println("Item not found");
				} else {
					System.out.println("Enter the new title:");
					String newTitle = scanner.nextLine();
					itemServ.updateTitle(itemFound.getItemId(), newTitle);
				}
				break;
			}
			case 2:{
				//Update Item Status
				List<Item> items = itemServ.getAllItems();
				for(Item i : items) {
					System.out.println(i);
				}
				System.out.println("Enter the Item Title whose status you want to change: ");
				String itemTitle = scanner.nextLine();
				Item itemFound = findItemByName(itemTitle, items);
				if(itemFound == null) {
					System.out.println("Item not found");
				} else {
					System.out.println("Enter a New Status (done/want/in_progress): ");
					String itemStatus = scanner.nextLine();
					int itemId = itemFound.getItemId();
					itemServ.updateStatus(itemId, itemStatus);
				}
				
				break;
			}
			case 3:{
				//Update Item Category
				List<Item> items = itemServ.getAllItems();
				List<Category> categories = serv.getAllCategories();
				for(Item i : items) {
					System.out.println(i);
				}
				System.out.println("Enter a Title to Update: ");
				String itemTitle = scanner.nextLine();
				Item itemFound = findItemByName(itemTitle, items);
				if(itemFound == null) {
					System.out.println("Item not found");
				} else {
					for(Category c : categories) {
					    System.out.println(c);
					}
					System.out.println("Enter a Category Name");
					String catName = scanner.nextLine();
					Category catFound = findCategoryByName(catName, categories);
					if(catFound == null) {
						System.out.println("Category Not Found, Go back to Categories to Manage");
					}else {
						itemServ.updateCategory(itemFound.getItemId(), catFound.getCategoryId());
						System.out.println("Item moved to " + catFound.getCategoryName() + " successfully.");
					}
				}
				break;
			}
			case 4:{
				//Exit
				System.out.println("Exiting...");
				itemEditChoice = 4;
				break;
			}
			default: {
			    System.out.println("Must enter an option 1-4");
			}	
		}
		}
	}
	
	private Item findItemByName(String name, List<Item> items){
		Item found = null;
		for(Item i : items) {
			if(i.getTitle().equalsIgnoreCase(name)){
				found = i;
				break;
			}
		}
		return found;
	}
	
	public void manageItemTagsStart() throws SQLException{
		int manageChoice = 0;
		while(manageChoice != 4) {
		System.out.println("Manage Item Tags: ");
		System.out.println("1. Add Tag to Item");
		System.out.println("2. Remove Tag from Item");
		System.out.println("3. View All Tags on Items");
		System.out.println("4. Back");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch(choice) {
			case 1: {
				//Add EXISTING Tag to Item
				List<Tag> tags = tagServ.getAllTags();
				List<Item> items = itemServ.getAllItems();
				for(Tag t : tags) {
				    System.out.println(t);
				}
				System.out.println("Enter a Tag Name: ");
				String tagName = scanner.nextLine();
				Tag foundTag = findTagByName(tagName, tags);
				if(foundTag == null){
					System.out.println("Tag not found");
				} else {
					for(Item i : items) {
						System.out.println(i);
					}
					System.out.println("Enter a Title to Add Tag to: ");
					String itemTitle = scanner.nextLine();
					Item foundTitle = findItemByName(itemTitle, items);
					if(foundTitle == null){
						System.out.println("Title not found, Go Back to Items to Manage");
					} else {
						itemServ.addTagtoItem(foundTitle.getItemId(), foundTag.getTagId() );
					}
				}
				break;
			}
			case 2: {
				//Remove EXISTING Tag From Item
				List<Item> items = itemServ.getAllItems();
				List<Tag> tags = tagServ.getAllTags();
				for(Tag t : tags) {
				    System.out.println(t);
				}
				System.out.println("Enter a Tag Name to Remove: ");
				String tagName = scanner.nextLine();
				Tag foundTag = findTagByName(tagName, tags);
				if(foundTag == null) {
					System.out.println("Tag not found");
				} else {
					for(Item i : items) {
					    System.out.println(i);
					}
					System.out.println("Enter an Item: ");
					String itemName = scanner.nextLine();
					Item foundItem = findItemByName(itemName, items);
					if(foundItem == null) {
						System.out.println("Item not found");
					} else {
					itemServ.removeTagFromItem(foundItem.getItemId(), foundTag.getTagId());
					}
				}
				break;
			}
			case 3: {
				//View EXISTING Tags on Item
				List<Item> items = itemServ.getAllItems();
				for(Item i : items) {
				    System.out.println(i);
				}
				System.out.println("Enter a Item Title: ");
				String itemName = scanner.nextLine();
				Item foundTitle = findItemByName(itemName, items);
				if(foundTitle == null){
					System.out.println("Item Title not found");
				} else {
					System.out.println(itemName + ":  " + itemServ.getTagsForItem(foundTitle.getItemId()));
				}
				break;
			}
			case 4: {
				//Exit
				System.out.println("Exiting...");
				manageChoice = 4;
				break;
			}
			default: {
				System.out.println("Must enter an option 1-4");
			}
		}
		}
	}

	
	public static void main(String[] args) throws SQLException {
	    CategoryRepository categoryRepo = new CategoryRepositoryImpl();
	    TagRepository tagRepo = new TagRepositoryImpl();
	    ItemRepository itemRepo = new ItemRepositoryImpl();
	    RatingRepository ratingRepo = new RatingRepositoryImpl();

	    CategoryService categoryService = new CategoryService(categoryRepo);
	    TagService tagService = new TagService(tagRepo);
	    ItemService itemService = new ItemService(itemRepo, categoryRepo, tagRepo);
	    RatingService ratingService = new RatingService(ratingRepo, itemRepo);

	    Scanner scanner = new Scanner(System.in);
	    ConsoleUI ui = new ConsoleUI(categoryService, itemService, ratingService, tagService, scanner);
	    ui.start();
	}

}
