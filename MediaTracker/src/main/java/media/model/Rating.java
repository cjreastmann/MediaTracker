package media.model;

import java.time.LocalDate;

public class Rating {
	private int ratingId;
	private int itemId;
	private int score;
	private String review;
	private LocalDate dateRated;
	
	//existing rating, known ids and local date
	public Rating(int ratingId, int itemId, int score, String review, LocalDate dateRated) {
		this.ratingId = ratingId;
		this.itemId = itemId;
		this.score = score;
		this.review = review;
		this.dateRated = dateRated;
		
	}
	
	//unknown rating, possible no review just score, local date added
	public Rating(int itemId, int score) {
		this(0, itemId, score, null, LocalDate.now());
	}

	
	//getters
	public int getRatingId() {
		return ratingId;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getReview() {
		return review;
	}
	
	public LocalDate getDateRated() {
		return dateRated;
	}
	
	//setters
	public void setScore(int s) {
		score = s;
	}
	
	public void setReview(String r) {
		review = r;
	}
	
}
