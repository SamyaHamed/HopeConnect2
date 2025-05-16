package com.example.Software_Advance.repositories;
import com.example.Software_Advance.models.Tables.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Software_Advance.models.Enums.*;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUserId(Long userId);

    List<Review> findByTargetIdAndTargetType(Long targetId, ReviewTargetType targetType);

    List<Review> findByRatingGreaterThanEqual(Integer rating);
    List<Review> findByRatingLessThanEqual(Integer rating);



}
