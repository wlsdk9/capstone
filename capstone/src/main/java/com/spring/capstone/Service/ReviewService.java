package com.spring.capstone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.capstone.DAO.ReviewDAO;
import com.spring.capstone.DTO.ReviewDTO;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewDAO reviewDAO;

    @Autowired
    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    // 모든 리뷰 정보 가져오기
    public List<ReviewDTO> getAllReviews() {
        return reviewDAO.getAllReviews();
    }

    // 새 리뷰 저장
    public void saveReview(ReviewDTO review) {
        reviewDAO.insertReview(review);
    }

    // 특정 리뷰 ID로 리뷰 가져오기
    public ReviewDTO getReviewById(int reviewId) {
        return reviewDAO.getReviewById(reviewId);
    }
 // 가져온 특정 리뷰에 대한 댓글 목록 가져오기
    public List<ReviewDTO> getCommentsForReview(int reviewId) {
        return reviewDAO.getCommentsForReview(reviewId);
    }
 // 새 댓글 저장
    public void saveComment(ReviewDTO comment) {
        reviewDAO.insertComment(comment);
    }


}
