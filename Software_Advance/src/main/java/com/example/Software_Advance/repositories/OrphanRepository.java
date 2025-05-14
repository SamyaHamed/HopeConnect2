package com.example.Software_Advance.repositories;

import com.example.Software_Advance.dto.OrphanHealthSummaryDTO;
import com.example.Software_Advance.models.Enums.*;
import com.example.Software_Advance.models.Tables.Orphan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrphanRepository extends JpaRepository<Orphan, Long> {

    Optional<Orphan> findById(Long id);

    List<Orphan> findByNameContainingIgnoreCase(String name);

    List<Orphan> findByAgeGreaterThan(int age);

    List<Orphan> findByEducationStatus(EducationStatus status);

    List<Orphan> findByHealthCondition(HealthCondition healthCondition);

    List<Orphan> findByOrphanageId(Long orphanageId);

    List<Orphan> findBySponsorId(Long sponsorId);

    @Query("SELECT new com.example.Software_Advance.dto.OrphanHealthSummaryDTO(o.healthCondition, COUNT(o)) " +
            "FROM Orphan o GROUP BY o.healthCondition")
    List<OrphanHealthSummaryDTO> getHealthSummaryReport();
}