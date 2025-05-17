package com.example.Software_Advance.services;

import com.example.Software_Advance.exceptions.VolunteerNotFoundException;
import com.example.Software_Advance.models.Enums.Availability;
import com.example.Software_Advance.models.Tables.Volunteer;
import com.example.Software_Advance.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class VolunteerService {

    private static final Logger log = LoggerFactory.getLogger(VolunteerService.class);

    @Autowired
    private VolunteerRepository volunteerRepository;

    public Volunteer saveVolunteer(Volunteer volunteer) {
        log.info("Saving volunteer: {}", volunteer.getUser().getName());
        return volunteerRepository.save(volunteer);
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new VolunteerNotFoundException(id));
    }

    public List<Volunteer> getVolunteersByOrganization(Long organizationId) {
        return volunteerRepository.findByOrganizationId(organizationId);
    }

    public List<Volunteer> getVolunteersByStatus(String status) {
        return volunteerRepository.findByStatus(status);
    }

    public List<Volunteer> getVolunteersBySkills(String skills) {
        return volunteerRepository.findBySkillsContaining(skills);
    }

    public List<Volunteer> getVolunteersByAvailability(Availability availability) {
        return volunteerRepository.findByAvailability(availability);
    }

    public void deleteVolunteer(Long id) {
        if (!volunteerRepository.existsById(id)) {
            log.warn("Volunteer not found with ID: {}", id);
            throw new VolunteerNotFoundException(id);
        }
        volunteerRepository.deleteById(id);
        log.info("Volunteer with ID {} deleted.", id);
    }
}
