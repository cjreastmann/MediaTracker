package media.ui;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import media.model.Category;
import media.model.Tag;
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
		System.out.println("3. View Items in Category by Name");
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
				System.out.println(categories);
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
				System.out.println(categories);
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
				System.out.println(categories);
				break;
			}
			case 5: {
				//Delete Category by Name
				List<Category> categories = serv.getAllCategories();
				System.out.println(categories);
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
				System.out.println(tags);
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
				System.out.println(tags);
			
				break;
			}
			case 4: {
				//Delete Tag
				List<Tag> tags = tagServ.getAllTags();
				System.out.println(tags);
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
