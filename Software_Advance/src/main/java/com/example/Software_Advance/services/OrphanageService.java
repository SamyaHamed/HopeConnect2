package com.example.Software_Advance.services;

import com.example.Software_Advance.dto.OrphanageWithVerificationDto;
import com.example.Software_Advance.models.Enums.verificationStatus;
import com.example.Software_Advance.models.Tables.Orphanage;
import com.example.Software_Advance.models.Tables.VerifiedOrphanage;
import com.example.Software_Advance.repositories.VerifiedOrphanageRepository;
import com.example.Software_Advance.repositories.OrphanageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrphanageService {

    @Autowired
    private OrphanageRepository orphanageRepository;

    @Autowired
    private VerifiedOrphanageRepository verifiedOrphanageRepository;

    public Orphanage saveOrphanage(Orphanage orphanage) {
        return orphanageRepository.save(orphanage);
    }

    public List<Orphanage> getOrphanagesByVerified(boolean verified) {
        return orphanageRepository.findByVerified(verified);
    }

    public List<Orphanage> getOrphanagesByCapacityGreaterThan(int capacity) {
        return orphanageRepository.findByCapacityGreaterThan(capacity);
    }

    public Orphanage updateOrphanage(Long id, Orphanage updatedOrphanage) {
        return orphanageRepository.findById(id).map(existing -> {
            existing.setCapacity(updatedOrphanage.getCapacity());
            existing.setOrphanCount(updatedOrphanage.getOrphanCount());
            existing.setVerified(updatedOrphanage.isVerified());
            return orphanageRepository.save(existing);
        }).orElse(null);
    }

    public Orphanage updateOrphanageCapacity(Long id, int newCapacity) {
        Optional<Orphanage> optionalOrphanage = orphanageRepository.findById(id);
        if (optionalOrphanage.isPresent()) {
            Orphanage orphanage = optionalOrphanage.get();
            orphanage.setCapacity(newCapacity);
            return orphanageRepository.save(orphanage);
        } else {
            throw new EntityNotFoundException("Orphanage not found with id: " + id);
        }
    }

    public Orphanage updateOrphanCount(Long id, int newCount) {
        Optional<Orphanage> optionalOrphanage = orphanageRepository.findById(id);
        if (optionalOrphanage.isPresent()) {
            Orphanage orphanage = optionalOrphanage.get();
            orphanage.setOrphanCount(newCount);
            return orphanageRepository.save(orphanage);
        } else {
            throw new EntityNotFoundException("Orphanage not found with id: " + id);
        }
    }

    // ✅ New method: Get orphanage with verification status
    public Optional<OrphanageWithVerificationDto> getOrphanageWithVerification(Long id) {
        Optional<Orphanage> orphanageOpt = orphanageRepository.findById(id);
        if (orphanageOpt.isPresent()) {
            Orphanage orphanage = orphanageOpt.get();

            // البحث عن أحدث توثيق لدار الأيتام
            Optional<VerifiedOrphanage> latestVerification =
                    verifiedOrphanageRepository.findTopByOrphanageIdOrderByVerificationDateDesc(orphanage.getId());

            // استخراج الحالة من التوثيق (إن وُجد) أو استخدام null في حال لم يوجد توثيق
            verificationStatus status = latestVerification
                    .map(VerifiedOrphanage::getStatus)
                    .orElse(null); // إذا لم يوجد توثيق، ستكون الحالة null

            // بناء الـ DTO باستخدام الـ orphanage والحالة
            OrphanageWithVerificationDto dto = new OrphanageWithVerificationDto(orphanage, status);
            return Optional.of(dto); // إرجاع الكائن المحوّل
        } else {
            return Optional.empty(); // إرجاع قيمة فارغة إذا لم تجد دار الأيتام
        }
    }

}
