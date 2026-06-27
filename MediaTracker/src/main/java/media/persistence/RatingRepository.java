package media.persistence;

import media.model.*;
import java.util.List;

public interface RatingRepository {
	public void save(Rating rating);
	Rating findById(int ratingId);
	List<Rating> findAll();
	public void updateScore(int ratingId, int score);
	public void updateReview(int ratingId, String review);
	List<Rating> findAllInItem(int itemId);
	public void delete(int ratingId);
	
}
