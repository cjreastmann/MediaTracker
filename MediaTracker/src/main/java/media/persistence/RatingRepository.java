package media.persistence;

import media.model.*;

import java.sql.SQLException;
import java.util.List;

public interface RatingRepository {
	public void save(Rating rating) throws SQLException;
	Rating findById(int ratingId) throws SQLException;
	List<Rating> findAll() throws SQLException;
	public void updateScore(int ratingId, int score) throws SQLException;
	public void updateReview(int ratingId, String review) throws SQLException;
	List<Rating> findAllInItem(int itemId) throws SQLException;
	public void delete(int ratingId) throws SQLException;
	
}
