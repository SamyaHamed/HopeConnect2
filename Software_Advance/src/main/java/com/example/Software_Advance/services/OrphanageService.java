package com.example.Software_Advance.services;
import com.example.Software_Advance.models.Tables.Orphanage;
import com.example.Software_Advance.repositories.OrphanageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;



@Service
public class OrphanageService {

    @Autowired
    private OrphanageRepository orphanageRepository;

    public Orphanage saveOrphanage(Orphanage orphanage) {
        return orphanageRepository.save(orphanage);
    }

    public List<Orphanage> getOrphanagesByVerified(boolean verified) {
        return orphanageRepository.findByVerified(verified);
    }

    public List<Orphanage> getOrphanagesByCapacityGreaterThan(int capacity) {
        List<Orphanage> orphanages = orphanageRepository.findByCapacityGreaterThan(capacity);
        if (orphanages.isEmpty()) {
            throw new EntityNotFoundException("No orphanages found with capacity greater than " + capacity);
        }
        return orphanages;
    }


    public Orphanage updateOrphanage(Long id, Orphanage updatedOrphanage) {
        Orphanage existing = orphanageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orphanage not found with id: " + id));

        existing.setCapacity(updatedOrphanage.getCapacity());
        existing.setOrphanCount(updatedOrphanage.getOrphanCount());
        existing.setVerified(updatedOrphanage.isVerified());
        return orphanageRepository.save(existing);
    }

    public Orphanage updateOrphanageCapacity(Long id, int newCapacity) {
        Orphanage orphanage = orphanageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orphanage not found with id: " + id));

        orphanage.setCapacity(newCapacity);
        return orphanageRepository.save(orphanage);
    }

    public Orphanage updateOrphanCount(Long id, int newCount) {
        Orphanage orphanage = orphanageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orphanage not found with id: " + id));

        orphanage.setOrphanCount(newCount);
        return orphanageRepository.save(orphanage);
    }
}
