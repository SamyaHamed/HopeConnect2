package com.example.Software_Advance.services;

import com.example.Software_Advance.models.Tables.Orphanage;
import com.example.Software_Advance.repositories.OrphanageRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})


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

}