package media.service;

import java.sql.SQLException;
import java.util.List;

import media.model.Rating;
import media.persistence.ItemRepository;
import media.persistence.RatingRepository;

public class RatingService {
	private RatingRepository repo;
	private ItemRepository itemRepo;
	
	public RatingService(RatingRepository repo, ItemRepository itemRepo) {
		this.repo = repo;
		this.itemRepo = itemRepo; 
	}
	
	private boolean isScoreValid(int score) {
		if(score >= 1 && score <= 10) {
			return true;
		}
		return false;
		
	}
	
	public void addRating(Rating rating) throws SQLException {
		if(!isScoreValid(rating.getScore())) {
			throw new IllegalArgumentException("Score must be between 1-10");
		}
		if(itemRepo.findById(rating.getItemId()) == null) {
			throw new IllegalArgumentException("Item with id " + rating.getItemId() + " does not exist.");

		}
		repo.save(rating);
	}
	
	public Rating getRating(int ratingId) throws SQLException{
		return repo.findById(ratingId);
	}
	
	public List<Rating> getAllRatings() throws SQLException{
		return repo.findAll();
	}
	
	public void updateReview(int ratingId, String review) throws SQLException {
		if(repo.findById(ratingId) == null) {
			throw new IllegalArgumentException("Rating with id " + ratingId + " does not exist.");

		}
		 repo.updateReview(ratingId, review);
	}
	
	public void updateScore(int ratingId, int score) throws SQLException {
		if(repo.findById(ratingId) == null) {
			throw new IllegalArgumentException("Rating with id " + ratingId + " does not exist.");

		}
		if(!isScoreValid(score)) {
			throw new IllegalArgumentException("Score must be between 1-10");
		}
		repo.updateScore(ratingId, score);
		
	}
	
	public List<Rating> getRatingsForItem(int itemId) throws SQLException{
		if(itemRepo.findById(itemId) == null) {
			throw new IllegalArgumentException("Item with id " + itemId + " does not exist.");
		}
		return repo.findAllInItem(itemId);
	}
	
	public void removeRating(int ratingId) throws SQLException {
		if(repo.findById(ratingId) == null) {
			throw new IllegalArgumentException("Rating with id " + ratingId + " does not exist.");	
		}
		repo.delete(ratingId);
	}
	
}
