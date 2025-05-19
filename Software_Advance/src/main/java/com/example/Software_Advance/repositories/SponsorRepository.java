package com.example.Software_Advance.repositories;
import com.example.Software_Advance.models.Tables.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    Optional<Sponsor> findByUserId(Long userId);

    List<Sponsor> findBySponsorshipType(String sponsorshipType);

    List<Sponsor> findByStatus(String status);

    List<Sponsor> findByStartDateAfter(LocalDate startDate);

    List<Sponsor> findBySponsorshipTypeAndStartDateAfter(String sponsorshipType, LocalDate startDate);
}