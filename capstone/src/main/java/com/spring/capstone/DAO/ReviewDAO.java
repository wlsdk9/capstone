package com.spring.capstone.DAO;

import com.spring.capstone.DTO.ReviewDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/cju_capstone?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Cjucapstone23";

    private static final String SELECT_ALL_REVIEWS = "SELECT r.reviews_id, r.cf_number_id, r.writeuser_id, r.title, r.content, r.rating, r.created_time, r.updated_at, cf.cf_name, cf.cf_number FROM reviews r INNER JOIN cf_numbers cf ON r.cf_number_id = cf.cf_number INNER JOIN users u ON r.writeuser_id = u.user_id";

    private static final String INSERT_REVIEW = "INSERT INTO reviews (cf_number_id, writeuser_id, title, content, rating) " +
            "VALUES ((SELECT cf_number FROM cf_numbers WHERE cf_number = ?), " +
            "(SELECT user_id FROM users WHERE user_id = ?), ?, ?, ?)";
    private static final String SELECT_REVIEW_BY_ID = "SELECT r.reviews_id, r.cf_number_id, r.writeuser_id, r.title, r.content, r.rating, r.created_time, r.updated_at, cf.cf_name, cf.cf_number FROM reviews r INNER JOIN cf_numbers cf ON r.cf_number_id = cf.cf_number INNER JOIN users u ON r.writeuser_id = u.user_id WHERE r.reviews_id = ?";

    private static final String SELECT_COMMENTS_FOR_REVIEW = "SELECT c.comment_id, c.user_id, c.reviews_id, c.content, c.created_at FROM comments c WHERE c.reviews_id = ?";
    private static final String INSERT_COMMENT = "INSERT INTO comments (user_id, reviews_id, content) VALUES (?, ?, ?)";

    public List<ReviewDTO> getAllReviews() {
        List<ReviewDTO> reviewList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_REVIEWS);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ReviewDTO review = new ReviewDTO();
                review.setReviewId(rs.getInt("reviews_id"));
                review.setCfNumberId(rs.getString("cf_number_id"));
                review.setUserId(rs.getString("writeuser_id"));
                review.setTitle(rs.getString("title"));
                review.setContent(rs.getString("content"));
                review.setRating(rs.getInt("rating"));
                review.setCreatedTime(rs.getString("created_time"));
                review.setUpdatedAt(rs.getString("updated_at"));
                review.setCfName(rs.getString("cf_name"));
                review.setCfNumber(rs.getString("cf_number"));

                reviewList.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviewList;
    }

    public ReviewDTO getReviewById(int reviewId) {
        ReviewDTO review = null;

        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_REVIEW_BY_ID)) {

            pstmt.setInt(1, reviewId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    review = new ReviewDTO();
                    review.setReviewId(rs.getInt("reviews_id"));
                    review.setCfNumberId(rs.getString("cf_number_id"));
                    review.setUserId(rs.getString("writeuser_id"));
                    review.setTitle(rs.getString("title"));
                    review.setContent(rs.getString("content"));
                    review.setRating(rs.getInt("rating"));
                    review.setCreatedTime(rs.getString("created_time"));
                    review.setUpdatedAt(rs.getString("updated_at"));
                    review.setCfName(rs.getString("cf_name"));
                    review.setCfNumber(rs.getString("cf_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return review;
    }

    public List<ReviewDTO> getCommentsForReview(int reviewId) {
        List<ReviewDTO> commentList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_COMMENTS_FOR_REVIEW)) {

            pstmt.setInt(1, reviewId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	ReviewDTO comment = new ReviewDTO();
                    comment.setCommentId(rs.getInt("comment_id"));
                    comment.setUserId(rs.getString("user_id"));
                    comment.setReviewId(rs.getInt("reviews_id"));
                    comment.setContent(rs.getString("content"));
                    comment.setCreatedAt(rs.getString("created_at"));

                    commentList.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;
    }

    public void insertComment(ReviewDTO comment) {
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(INSERT_COMMENT)) {

            pstmt.setString(1, comment.getUserId());
            pstmt.setInt(2, comment.getReviewId());
            pstmt.setString(3, comment.getContent());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertReview(ReviewDTO review) {
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REVIEW)) {

            pstmt.setString(1, review.getCfNumberId());
            pstmt.setString(2, review.getUserId());
            pstmt.setString(3, review.getTitle());
            pstmt.setString(4, review.getContent());
            pstmt.setInt(5, review.getRating());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
