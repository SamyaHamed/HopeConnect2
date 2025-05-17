package com.example.Software_Advance.services;

import com.example.Software_Advance.models.Enums.SponsorshipType;
import com.example.Software_Advance.models.Tables.Sponsor;
import com.example.Software_Advance.repositories.SponsorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class SponsorService {

    private final SponsorRepository sponsorRepository;

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    public Sponsor saveSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    public Sponsor updateSponsor(Long id, Sponsor updatedSponsor) {
        return sponsorRepository.findById(id)
                .map(existingSponsor -> {
                    if (updatedSponsor.getSponsorshipType() != null) {
                        existingSponsor.setSponsorshipType(updatedSponsor.getSponsorshipType());
                    }
                    if (updatedSponsor.getStatus() != null) {
                        existingSponsor.setStatus(updatedSponsor.getStatus());
                    }
                    if (updatedSponsor.getStartDate() != null) {
                        existingSponsor.setStartDate(updatedSponsor.getStartDate());
                    }
                    return sponsorRepository.save(existingSponsor);
                }).orElseThrow(() -> new EntityNotFoundException("Sponsor not found with id: " + id));
    }

    @Transactional
    public void deleteSponsor(Long id) {
        sponsorRepository.deleteById(id);
    }
}
