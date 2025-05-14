package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.EducationStatus;
import com.example.Software_Advance.models.Enums.HealthCondition;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "orphan")
public class Orphan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Min(0)
    @Column(name = "age", nullable = false)
    private int age;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "education_status", length = 50, nullable = false)
    private EducationStatus educationStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "health_condition", length = 50 , nullable = false)
    private HealthCondition healthCondition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_id")
    private Sponsor sponsor;
}