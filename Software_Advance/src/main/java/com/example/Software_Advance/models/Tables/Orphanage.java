package com.example.Software_Advance.models.Tables;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orphanage")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orphanage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;


    @NotNull(message = "Capacity is required")
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @NotNull(message = "Orphan count is required")
    @Column(name = "orphan_count", nullable = false)
    private int orphanCount;

    @Column(name = "verified", nullable = false)
    private boolean verified;

    @OneToMany(mappedBy = "orphanage")
    @JsonManagedReference
    private List<Orphan> orphans;



}
