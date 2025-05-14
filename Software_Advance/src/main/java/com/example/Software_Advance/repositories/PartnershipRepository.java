/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.Partnership;
import com.example.Software_Advance.models.Enums.PartnershipStatus;
import com.example.Software_Advance.models.Enums.PartnershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PartnershipRepository extends JpaRepository<Partnership, Long> {

    List<Partnership> findByPartnershipType(PartnershipType partnershipType);

    List<Partnership> findByStatus(PartnershipStatus status);

    List<Partnership> findByEndDateAfter(LocalDate date);

    List<Partnership> findByEndDateIsNull();

    Partnership findByEmail(String email);
}*/