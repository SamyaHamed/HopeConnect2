package com.example.Software_Advance.services;

import com.example.Software_Advance.dto.ReviewDto;
import com.example.Software_Advance.models.Enums.ReviewTargetType;
import com.example.Software_Advance.models.Tables.Review;
import com.example.Software_Advance.models.Tables.User;
import com.example.Software_Advance.repositories.ReviewRepository;
import com.example.Software_Advance.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public ReviewDto addReview(Long targetId, ReviewTargetType targetType, Long userId, Integer rating, String reviewText) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Review review = new Review();
        review.setTargetId(targetId);
        review.setTargetType(targetType);
        review.setUser(user);
        review.setRating(rating);
        review.setReviewText(reviewText);
        review.setCreatedAt(LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);

        return new ReviewDto(
                savedReview.getTargetId(),
                savedReview.getTargetType(),
                savedReview.getUser().getId(),
                savedReview.getRating(),
                savedReview.getReviewText()
        );
    }

    public ReviewDto updateReview(Long reviewId, Integer newRating, String newReviewText) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        review.setRating(newRating);
        review.setReviewText(newReviewText);

        Review updatedReview = reviewRepository.save(review);

        return new ReviewDto(
                updatedReview.getTargetId(),
                updatedReview.getTargetType(),
                updatedReview.getUser().getId(),
                updatedReview.getRating(),
                updatedReview.getReviewText()
        );
    }

    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new EntityNotFoundException("Review with ID " + reviewId + " not found");
        }
        reviewRepository.deleteById(reviewId);
    }


    public List<Review> getReviewsByTarget(Long targetId, ReviewTargetType targetType) {
        return reviewRepository.findByTargetIdAndTargetType(targetId, targetType);
    }

    public List<Review> getReviewsByRating(int rating) {
        return reviewRepository.findByRatingGreaterThanEqual(rating);
    }
}
