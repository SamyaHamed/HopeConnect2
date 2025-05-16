package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.ReviewTargetType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    @NotNull
    private Long targetId;
    @NotNull
    private ReviewTargetType targetType;

    @NotNull
    private Long userId;

    @Min(1)
    @Max(5)
    private int rating;

    private String reviewText;
}


