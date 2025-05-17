package com.example.Software_Advance.services;

import com.example.Software_Advance.exceptions.ResourceNotFoundException;
import com.example.Software_Advance.models.Enums.ParticipationStatus;
import com.example.Software_Advance.models.Tables.OrgVolunteer;
import com.example.Software_Advance.repositories.OrgVolunteerRepository;
import com.example.Software_Advance.repositories.OrganizationRepository;
import com.example.Software_Advance.repositories.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgVolunteerService {
    private static final Logger logger = LoggerFactory.getLogger(OrgVolunteerService.class);

    @Autowired
    private OrgVolunteerRepository orgVolunteerRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public OrgVolunteer saveOrgVolunteer(OrgVolunteer orgVolunteer) {
        logger.info("Saving OrgVolunteer: {}", orgVolunteer);
        OrgVolunteer savedOrgVolunteer = orgVolunteerRepository.save(orgVolunteer);
        logger.info("Saved OrgVolunteer: {}", savedOrgVolunteer);
        return savedOrgVolunteer;
    }

    public List<OrgVolunteer> getAllOrgVolunteers() {
        return orgVolunteerRepository.findAll();
    }
    public List<OrgVolunteer> getVolunteersByOrganizationId(Long orgId) {
        return orgVolunteerRepository.findByOrganization_Id(orgId);
    }

    public List<OrgVolunteer> getOrganizationsByVolunteerId(Long volunteerId) {
        return orgVolunteerRepository.findByVolunteer_Id(volunteerId);
    }

    public List<OrgVolunteer> filterVolunteers(String skill, String availability) {
        return orgVolunteerRepository.filterBySkillAndAvailability(skill, availability);
    }

    public OrgVolunteer updateParticipationStatus(Long id, ParticipationStatus newStatus) {
        OrgVolunteer rec = orgVolunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrgVolunteer not found: " + id));
        rec.setParticipationStatus(newStatus);
        return orgVolunteerRepository.save(rec);
    }

    public List<OrgVolunteer> filterVolunteers(String skill, ParticipationStatus status) {
        return orgVolunteerRepository.filterBySkillAndStatus(skill, status);
    }


}