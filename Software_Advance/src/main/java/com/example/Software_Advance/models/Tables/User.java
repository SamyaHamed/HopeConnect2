package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format"
    )
    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Address is required")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number")
    @Column(name = "phone", nullable = false)
    private String phone;


    @NotBlank(message = "User type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable =true)
    private UserType type;


    @NotBlank(message = "User role is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Sponsor sponsor;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true ,optional = true ,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Donor donor;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Volunteer volunteer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Organization organization;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Orphanage orphanage;


}





