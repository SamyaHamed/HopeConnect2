package com.example.Software_Advance.services;

import com.example.Software_Advance.models.Tables.Orphanage;
import com.example.Software_Advance.models.Enums.verificationStatus;
import com.example.Software_Advance.models.Tables.VerifiedOrphanage;
import com.example.Software_Advance.repositories.OrphanageRepository;
import com.example.Software_Advance.repositories.VerifiedOrphanageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VerifiedOrphanageService {

    @Autowired
    private VerifiedOrphanageRepository verifiedOrphanageRepository;

    @Autowired
    private OrphanageRepository orphanageRepository;

    public VerifiedOrphanage saveVerification(Long orphanageId, Long adminId, verificationStatus status) {

        Orphanage orphanage = orphanageRepository.findById(orphanageId)
                .orElseThrow(() -> new EntityNotFoundException("Orphanage with id " + orphanageId + " not found"));

        VerifiedOrphanage verifiedOrphanage = new VerifiedOrphanage();
        verifiedOrphanage.setOrphanage(orphanage);
        verifiedOrphanage.setVerifiedByAdmin(adminId);
        verifiedOrphanage.setVerificationDate(LocalDate.now());
        verifiedOrphanage.setStatus(status);


        try {
            return verifiedOrphanageRepository.save(verifiedOrphanage);
        } catch (Exception e) {
            throw new RuntimeException("Error saving the verification: " + e.getMessage(), e);
        }
    }

    public List<VerifiedOrphanage> getByAdminId(Long adminId) {
        try {
            return verifiedOrphanageRepository.findByVerifiedByAdmin(adminId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching orphanages by admin: " + e.getMessage(), e);
        }
    }

    public List<VerifiedOrphanage> getByStatus(verificationStatus status) {
        try {
            return verifiedOrphanageRepository.findByStatus(status);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching orphanages by status: " + e.getMessage(), e);
        }
    }

    public List<VerifiedOrphanage> getByVerificationDate(LocalDate verificationDate) {
        try {
            return verifiedOrphanageRepository.findByVerificationDate(verificationDate);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching orphanages by verification date: " + e.getMessage(), e);
        }
    }

    public List<VerifiedOrphanage> getByAdminAndStatus(Long adminId, verificationStatus status) {
        try {
            return verifiedOrphanageRepository.findByVerifiedByAdminAndStatus(adminId, status);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching orphanages by admin and status: " + e.getMessage(), e);
        }
    }

    public Optional<VerifiedOrphanage> getLatestVerification(Long orphanageId) {
        try {
            return verifiedOrphanageRepository.findTopByOrphanageIdOrderByVerificationDateDesc(orphanageId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching latest verification for orphanage: " + e.getMessage(), e);
        }
    }

    public Optional<VerifiedOrphanage> getLatestVerified(Long orphanageId, verificationStatus status) {
        try {
            return verifiedOrphanageRepository.findTopByOrphanageIdAndStatusOrderByVerificationDateDesc(orphanageId, status);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching latest verification for orphanage with status: " + e.getMessage(), e);
        }
    }
}
