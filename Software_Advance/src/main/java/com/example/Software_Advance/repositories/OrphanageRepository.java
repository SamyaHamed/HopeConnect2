package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.Orphanage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrphanageRepository extends JpaRepository<Orphanage, Long> {

    Optional<Orphanage> findByUserId(Long userId);

    List<Orphanage> findByVerified(boolean verified);

    List<Orphanage> findByCapacityGreaterThan(int capacity);

    // List<Orphanage> findByCurrentOrphansGreaterThan(int currentOrphans);


}