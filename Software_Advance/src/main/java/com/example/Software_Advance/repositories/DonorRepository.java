package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {

    Optional<Donor> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}