 package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.ServiceType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
            import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

 @Entity
    @Table(name = "organization")
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Organization {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JsonBackReference
        @JoinColumn(name = "user_id", nullable = false, unique = true)
        private User user;

        @NotBlank(message = "Service type is required")
        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private ServiceType serviceType;

        @JsonIgnore
        @JsonManagedReference
        @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
        private List<OrgVolunteer> orgVolunteers;

    }
