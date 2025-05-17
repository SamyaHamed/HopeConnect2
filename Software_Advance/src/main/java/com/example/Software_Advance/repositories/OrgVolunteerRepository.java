package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Enums.ParticipationStatus;
import com.example.Software_Advance.models.Tables.OrgVolunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrgVolunteerRepository extends JpaRepository<OrgVolunteer, Long> {
    List<OrgVolunteer> findByVolunteer_Id(Long volunteerId);
    List<OrgVolunteer> findByOrganization_Id(Long organizationId);
    List<OrgVolunteer> findBySkillsContaining(String skill);
    void deleteByVolunteerId(Long volunteerId);

    @Query("SELECT o FROM OrgVolunteer o WHERE " +
            "(:skill IS NULL OR o.skills LIKE %:skill%) AND " +
            "(:status IS NULL OR o.participationStatus = :status)")
    List<OrgVolunteer> filterBySkillAndStatus(String skill, ParticipationStatus status);


    @Query("SELECT o FROM OrgVolunteer o WHERE " +
            "(:skill IS NULL OR o.skills = :skill) AND " +
            "(:availability IS NULL OR o.volunteer.availability = :availability)")
    List<OrgVolunteer> filterBySkillAndAvailability(String skill, String availability);

    @Query("SELECT o.organization.id, COUNT(o) FROM OrgVolunteer o GROUP BY o.organization.id")
    List<Object[]> countVolunteersByOrganization();
}