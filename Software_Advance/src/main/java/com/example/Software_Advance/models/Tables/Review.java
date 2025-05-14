/*package com.example.Software_Advance.models.Tables;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = true)
    private Long organizationId;

    @ManyToOne
    @JoinColumn(name = "orphanage_id", nullable = true)
    private Orphanage orphanage; // Assuming Orphanage is another entity class

    @Min(1)
    @Max(5)
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(nullable = true)
    private String reviewText;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
*/