package com.example.Software_Advance.models.Tables;
import com.example.Software_Advance.models.Enums.SponsorshipType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "sponsor")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;



    @NotBlank(message = "Sponsorship type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "sponsorship_type", nullable = false)
    private SponsorshipType sponsorshipType;


    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false)
    private String status;
}
