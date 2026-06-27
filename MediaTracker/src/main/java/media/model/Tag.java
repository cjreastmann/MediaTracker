package media.model;

public class Tag {
	private int tagId;
	private String tagName;
	
	//existing tag, known id and name
	public Tag(int tagId, String name) {
		this.tagId = tagId;
		this.tagName = name;
	}
	
	//tag creation, unknown id, name required
	public Tag(String name) {
		this(0, name);
	}
	
	//getters
	public int getTagId() {
		return tagId;
	}
	
	public String getTagName() {
		return tagName;
	}
	
	//setters
	public void setTagName(String t) {
		tagName = t;
	}
	
	@Override
	public String toString() {
		return "Tags: id = " + tagId + ",   name = " + tagName; 
	}
	
}
