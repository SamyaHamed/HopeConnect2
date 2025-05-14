/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.VerifiedOrphanage;
import com.example.Software_Advance.models.Enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VerifiedOrphanageRepository extends JpaRepository<VerifiedOrphanage, Long> {

    List<VerifiedOrphanage> findByVerifiedByAdmin(Long adminId);

    List<VerifiedOrphanage> findByStatus(VerificationStatus status);

    List<VerifiedOrphanage> findByVerificationDate(LocalDate verificationDate);

    List<VerifiedOrphanage> findByVerifiedByAdminAndStatus(Long adminId, VerificationStatus status);
}*/