/*package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.PartnershipType;
import com.example.Software_Advance.models.Enums.PartnershipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;



import java.time.LocalDate;

@Entity
@Table(name = "partnerships")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partnership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "contact_person", nullable = false)
    private String contactPerson;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format"
    )
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "partnership_type", nullable = false)
    private PartnershipType partnershipType;

    @Column(name = "agreement_date", nullable = false)
    private LocalDate agreementDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PartnershipStatus status;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;
}
*/