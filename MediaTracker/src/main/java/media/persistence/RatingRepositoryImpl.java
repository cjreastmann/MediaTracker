package media.persistence;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import media.model.Rating;

public class RatingRepositoryImpl implements RatingRepository {

    public void save(Rating rating) throws SQLException {
        String sql = "INSERT INTO rating (item_id, score, review, date_rated) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, rating.getItemId());
            stmt.setInt(2, rating.getScore());
            stmt.setString(3, rating.getReview());
            stmt.setDate(4, Date.valueOf(rating.getDateRated()));

            stmt.executeUpdate();
        }
    }

    public Rating findById(int ratingId) throws SQLException {
        String sql = "SELECT * FROM rating WHERE rating_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, ratingId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int ratingID = rs.getInt("rating_id");
                    int itemId = rs.getInt("item_id");
                    int score = rs.getInt("score");
                    String review = rs.getString("review");
                    LocalDate dateRated = rs.getDate("date_rated").toLocalDate();

                    return new Rating(ratingID, itemId, score, review, dateRated);
                }
            }
        }

        return null;
    }

    public List<Rating> findAll() throws SQLException {
        String sql = "SELECT * FROM rating";
        List<Rating> ratings = new ArrayList<>();

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                int ratingID = rs.getInt("rating_id");
                int itemId = rs.getInt("item_id");
                int score = rs.getInt("score");
                String review = rs.getString("review");
                LocalDate dateRated = rs.getDate("date_rated").toLocalDate();

                ratings.add(new Rating(ratingID, itemId, score, review, dateRated));
            }
        }

        return ratings;
    }

    public void updateScore(int ratingId, int score) throws SQLException {
        String sql = "UPDATE rating SET score = ? WHERE rating_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, score);
            stmt.setInt(2, ratingId);

            stmt.executeUpdate();
        }
    }

    public void updateReview(int ratingId, String review) throws SQLException {
        String sql = "UPDATE rating SET review = ? WHERE rating_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, review);
            stmt.setInt(2, ratingId);

            stmt.executeUpdate();
        }
    }

    public List<Rating> findAllInItem(int itemId) throws SQLException {
        String sql = "SELECT * FROM rating WHERE item_id = ?";
        List<Rating> ratings = new ArrayList<>();

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, itemId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int ratingID = rs.getInt("rating_id");
                    int itemID = rs.getInt("item_id");
                    int score = rs.getInt("score");
                    String review = rs.getString("review");
                    LocalDate dateRated = rs.getDate("date_rated").toLocalDate();

                    ratings.add(new Rating(ratingID, itemID, score, review, dateRated));
                }
            }
        }

        return ratings;
    }

    public void delete(int ratingId) throws SQLException {
        String sql = "DELETE FROM rating WHERE rating_id = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, ratingId);

            stmt.executeUpdate();
        }
    }
}
