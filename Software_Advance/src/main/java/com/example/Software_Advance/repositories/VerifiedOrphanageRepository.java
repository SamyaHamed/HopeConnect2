package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Enums.verificationStatus;
import com.example.Software_Advance.models.Tables.VerifiedOrphanage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VerifiedOrphanageRepository extends JpaRepository<VerifiedOrphanage, Long> {

    List<VerifiedOrphanage> findByVerifiedByAdmin(Long adminId);

    List<VerifiedOrphanage> findByStatus(verificationStatus status);

    List<VerifiedOrphanage> findByVerificationDate(LocalDate verificationDate);

    List<VerifiedOrphanage> findByVerifiedByAdminAndStatus(Long adminId, verificationStatus status);

    Optional<VerifiedOrphanage> findTopByOrphanageIdOrderByVerificationDateDesc(Long orphanageId);

    Optional<VerifiedOrphanage> findTopByOrphanageIdAndStatusOrderByVerificationDateDesc(Long orphanageId, verificationStatus status);
}
