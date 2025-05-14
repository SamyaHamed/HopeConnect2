package com.example.Software_Advance.services;

import com.example.Software_Advance.dto.OrphanDTO;
import com.example.Software_Advance.models.Tables.Orphan;
import com.example.Software_Advance.models.Tables.Orphanage;
import com.example.Software_Advance.models.Tables.Sponsor;
import com.example.Software_Advance.repositories.OrphanRepository;
import com.example.Software_Advance.repositories.OrphanageRepository;
import com.example.Software_Advance.repositories.SponsorRepository;
import com.example.Software_Advance.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrphanService {

    private final OrphanRepository orphanRepository;
    private final UserRepository userRepository;
    private final OrphanageRepository orphanageRepository;
    private final SponsorRepository sponsorRepository;

    @Transactional
    public Orphan createOrphan(OrphanDTO orphanDTO) {

        Orphanage orphanage = orphanageRepository.findById(orphanDTO.getOrphanageId())
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Orphanage not found"));

        Orphan orphan = new Orphan();
        orphan.setName(orphanDTO.getName());
        orphan.setAge(orphanDTO.getAge());
        orphan.setEducationStatus(orphanDTO.getEducationStatus());
        orphan.setHealthCondition(orphanDTO.getHealthCondition());
        orphan.setOrphanage(orphanage);

        if (orphanDTO.getSponsorId() != null) {
            Sponsor sponsor = sponsorRepository.findById(orphanDTO.getSponsorId())
                    .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Sponsor not found"));
            orphan.setSponsor(sponsor);
        }

        orphanRepository.save(orphan);
        return orphan;

    }


    public Optional<OrphanDTO> getOrphanById(Long id) {
        return orphanRepository.findById(id)
                .map(OrphanDTO::new);
    }

    public List<OrphanDTO> getAllOrphans() {
        return orphanRepository.findAll().stream()
                .map(OrphanDTO::new)
                .toList();
    }


    public List<OrphanDTO> searchOrphansByName(String name) {
        return orphanRepository.findByNameContainingIgnoreCase(name).stream()
                .map(OrphanDTO::new)
                .toList();
    }



    public void deleteOrphan(Long id) {
        if (!orphanRepository.existsById(id)) {
            throw new RuntimeException("Orphan not found with id: " + id);
        }
        orphanRepository.deleteById(id);
    }

  @Transactional
    public Orphan updateOrphan(Long id, OrphanDTO orphanDTO) {
        Orphan orphan = orphanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orphan not found"));

        orphan.setName(orphanDTO.getName());
        orphan.setAge(orphanDTO.getAge());
        orphan.setEducationStatus(orphanDTO.getEducationStatus());
        orphan.setHealthCondition(orphanDTO.getHealthCondition());

        Orphanage orphanage = orphanageRepository.findById(orphanDTO.getOrphanageId())
                .orElseThrow(() -> new RuntimeException("Orphanage not found"));
        orphan.setOrphanage(orphanage);

        if (orphanDTO.getSponsorId() != null) {
            Sponsor sponsor = sponsorRepository.findById(orphanDTO.getSponsorId())
                    .orElseThrow(() -> new RuntimeException("Sponsor not found"));
            orphan.setSponsor(sponsor);
        } else {
            orphan.setSponsor(null);
        }

        orphanRepository.save(orphan);
        return orphan;
    }




}
