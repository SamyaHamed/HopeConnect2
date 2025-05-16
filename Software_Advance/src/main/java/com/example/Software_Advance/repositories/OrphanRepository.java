/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.Orphan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrphanRepository extends JpaRepository<Orphan, Long> {

    Orphan findByUserId(Long userId);

    List<Orphan> findByNameContainingIgnoreCase(String name);

    List<Orphan> findByAgeGreaterThan(int age);

    List<Orphan> findByEducationStatusContainingIgnoreCase(String educationStatus);

    List<Orphan> findByHealthConditionContainingIgnoreCase(String healthCondition);

    List<Orphan> findByOrphanageId(Long orphanageId);

    List<Orphan> findBySponsorId(Long sponsorId);

    Orphan findByName(String name);
}*/