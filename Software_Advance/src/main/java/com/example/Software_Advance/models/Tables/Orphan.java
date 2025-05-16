/*package com.example.Software_Advance.models.Tables;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orphan")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orphan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orphan_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "orphan_id", nullable = false)
    private User user;

    @NotBlank
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Min(0)
    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "education_status", length = 255 , nullable = false)
    private String educationStatus;

    @Column(name = "health_condition", length = 255 , nullable = false)
    private String healthCondition;

    @ManyToOne
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;

    @ManyToOne
    @JoinColumn(name = "sponsor_id", nullable = true)
    private Sponsor sponsor;
}*/