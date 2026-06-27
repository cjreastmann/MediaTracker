package media.persistence;

import java.util.List;
import media.model.*;

public interface TagRepository {
	public void save(Tag tag);
	Tag findById(int tagId);
	List<Tag> findAll();
	public void updateName(int tagId, String tagName);
	public void delete(int tagId);

}
