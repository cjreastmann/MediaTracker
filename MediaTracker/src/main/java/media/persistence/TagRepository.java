package media.persistence;

import java.sql.SQLException;
import java.util.List;
import media.model.*;

public interface TagRepository {
	public void save(Tag tag) throws SQLException;
	Tag findById(int tagId) throws SQLException;
	List<Tag> findAll() throws SQLException;
	public void updateName(int tagId, String tagName) throws SQLException;
	public void delete(int tagId) throws SQLException;

}
