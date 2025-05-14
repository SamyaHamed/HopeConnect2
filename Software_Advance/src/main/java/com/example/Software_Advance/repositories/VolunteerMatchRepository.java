/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.VolunteerMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VolunteerMatchRepository extends JpaRepository<VolunteerMatch, Long> {

    List<VolunteerMatch> findByMatchStatus(Boolean matchStatus);

    List<VolunteerMatch> findByRequestId(Long requestId);

    List<VolunteerMatch> findByVolunteerId(Long volunteerId);

    List<VolunteerMatch> findByMatchedAt(LocalDateTime matchedAt);

    List<VolunteerMatch> findByVolunteerIdAndRequestId(Long volunteerId, Long requestId);
}*/