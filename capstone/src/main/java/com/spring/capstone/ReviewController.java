package com.spring.capstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.capstone.DTO.ReviewDTO;
import com.spring.capstone.Service.ReviewService;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 상세 페이지 보기
    @GetMapping("/view/goReviewDetailView")
    public String showReviewDetail(@RequestParam("reviewId") int reviewId, Model model) {
        // 리뷰 ID로 리뷰 정보 가져오기
        ReviewDTO review = reviewService.getReviewById(reviewId);
        if (review == null) {
            return "error";
        }
        model.addAttribute("review", review);

        // 해당 리뷰에 대한 댓글 가져오기
        List<ReviewDTO> comments = reviewService.getCommentsForReview(reviewId);
        model.addAttribute("comments", comments);

        return "goReviewDetailView"; // 리뷰 상세 페이지 JSP 이름으로 변경
    }

    // 리뷰 목록 페이지 보기
    @GetMapping("/view/goReviewListView")
    public String showReviewList(Model model) {
        // 모든 리뷰 정보 가져오기
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "goReviewListView"; // 리뷰 목록 페이지 JSP 이름으로 변경
    }

    // 리뷰 작성 폼 보여주기
    @RequestMapping(value = "/view/goReviewWriteView", method = RequestMethod.GET)
    public String showCreateReviewForm() {
        return "goReviewWriteView"; // 리뷰 작성 폼 JSP 이름으로 변경
    }

    // 리뷰 작성 처리
    @RequestMapping(value = "/view/goReviewListView", method = RequestMethod.POST)
    public String createReview(@RequestParam("cfName") String cfName,
                               @RequestParam("cfNumber") String cfNumber,
                               @RequestParam("rating") int rating,
                               @RequestParam("title") String title,
                               @RequestParam("content") String content,
                               HttpSession session,
                               Model model) {
        String writeUserId = (String) session.getAttribute("userId");

        if (writeUserId == null) {
            throw new IllegalStateException("User ID not found in session.");
        }

        // 새 리뷰 DTO 생성 및 데이터 설정
        ReviewDTO review = new ReviewDTO();
        review.setUserId(writeUserId);
        review.setCfName(cfName);
        review.setCfNumber(cfNumber);
        review.setCfNumberId(cfNumber);
        review.setRating(rating);
        review.setTitle(title);
        review.setContent(content);

        // 리뷰 서비스를 통해 리뷰 저장
        reviewService.saveReview(review);

        model.addAttribute("message", "Review created successfully!");
        return "redirect:/view/goReviewListView"; // 리뷰 목록 페이지로 리다이렉트
    }

    // 댓글 작성 처리
    @PostMapping("/view/addComment")
    public String addComment(@RequestParam("reviewId") int reviewId,
                             @RequestParam("commentContent") String commentContent,
                             HttpSession session,
                             Model model) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("User ID not found in session.");
        }

        // 새 댓글 DTO 생성 및 데이터 설정
        ReviewDTO comment = new ReviewDTO();
        comment.setUserId(userId);
        comment.setReviewId(reviewId);
        comment.setContent(commentContent);

        // 댓글 서비스를 통해 댓글 저장
        reviewService.saveComment(comment);

        model.addAttribute("message", "Comment added successfully!");
        return "redirect:/view/goReviewDetailView?reviewId=" + reviewId; // 리뷰 상세 페이지로 리다이렉트
    }
}
