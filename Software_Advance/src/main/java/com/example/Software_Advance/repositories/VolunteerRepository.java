package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Enums.Availability;
import com.example.Software_Advance.models.Tables.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    List<Volunteer> findByOrganizationId(Long organizationId);

    List<Volunteer> findByStatus(String status);

    List<Volunteer> findBySkillsContaining(String skills);

    List<Volunteer> findByAvailability(Availability availability);

    Optional<Volunteer> findByUserId(Long userId);
    List<Volunteer> findByStatusIgnoreCase(String status);


}