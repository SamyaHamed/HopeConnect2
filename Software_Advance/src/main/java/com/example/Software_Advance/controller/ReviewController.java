package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.ReviewDto;
import com.example.Software_Advance.models.Enums.ReviewTargetType;
import com.example.Software_Advance.models.Tables.Review;
import com.example.Software_Advance.services.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Review")
public class ReviewController {

    @Autowired
    private ReviewService reviewsService;

    @PostMapping("/create")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto request) {
        ReviewDto review = reviewsService.addReview(
                request.getTargetId(),
                request.getTargetType(),
                request.getUserId(),
                request.getRating(),
                request.getReviewText()
        );

        return ResponseEntity.ok("Review added successfully!");
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody ReviewDto updatedReviewData) {
        ReviewDto updatedReview = reviewsService.updateReview(reviewId, updatedReviewData.getRating(), updatedReviewData.getReviewText());
        return ResponseEntity.ok("Review updated successfully!");
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        reviewsService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted successfully!");
    }

    @GetMapping("/by-target")
    public ResponseEntity<List<ReviewDto>> getReviewsByTarget(@RequestParam Long targetId,
                                                              @RequestParam ReviewTargetType targetType) {
        List<Review> reviews = reviewsService.getReviewsByTarget(targetId, targetType);
        List<ReviewDto> dtos = reviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/by-rating")
    public ResponseEntity<List<ReviewDto>> getReviewsByRating(@RequestParam int rating) {
        List<Review> reviews = reviewsService.getReviewsByRating(rating);
        List<ReviewDto> dtos = reviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private ReviewDto convertToDTO(Review review) {
        return new ReviewDto(
                review.getTargetId(),
                review.getTargetType(),
                review.getUser().getId(),
                review.getRating(),
                review.getReviewText()
        );
    }


    // ===== Exception Handlers =====

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
    }
}
