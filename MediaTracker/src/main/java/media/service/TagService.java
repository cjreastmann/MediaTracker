package media.service;

import java.sql.SQLException;
import java.util.List;

import media.model.Tag;
import media.persistence.TagRepository;

public class TagService {
	private TagRepository repo;

	public TagService(TagRepository repo) {
		this.repo = repo;
	}

	public void addTag(Tag tag) throws SQLException {
	    if (tag.getTagName() == null || tag.getTagName().isBlank()) {
	    	throw new IllegalArgumentException("Tag name cannot be blank!");
	    }
	    repo.save(tag);
	}
	
	public Tag getTag(int tagId) throws SQLException {
		return repo.findById(tagId);
	}
	
	public List<Tag> getAllTags() throws SQLException{
		return repo.findAll();
	}
	
	public void updateTag(int tagId, String tagName) throws SQLException {
		if (tagName == null || tagName.isBlank()) {
	    	throw new IllegalArgumentException("Tag name cannot be blank!");
	    }
		repo.updateName(tagId, tagName);
	}
	
	public void removeTag(int tagId) throws SQLException {
		if(repo.findById(tagId) == null) {
			throw new IllegalArgumentException("Tag with id " + tagId + " does not exist.");
		}
		repo.delete(tagId);
	}

}
