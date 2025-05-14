 package com.example.Software_Advance.models.Tables;
import com.example.Software_Advance.models.Enums.Availability;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
 import jakarta.validation.constraints.NotBlank;
import lombok.*;

    @Entity
    @Table(name = "volunteer")
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Volunteer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JsonBackReference
        @JoinColumn(name = "user_id", nullable = false, unique = true)
        private User user;

        @Column(name = "organization_id", nullable = true)
        private Long organizationId;

        @NotBlank(message = "Skills are required")
        @Column(name = "skills", nullable = false)
        private String skills;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Availability availability;

        @NotBlank(message = "Status is required")
        @Column(name = "status", nullable = false)
        private String status;
    }
