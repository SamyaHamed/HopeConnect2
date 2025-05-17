package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.OrgVolunteerDto;
import com.example.Software_Advance.exceptions.InvalidRequestException;
import com.example.Software_Advance.exceptions.ResourceNotFoundException;
import com.example.Software_Advance.models.Enums.ParticipationStatus;
import com.example.Software_Advance.models.Tables.OrgVolunteer;
import com.example.Software_Advance.models.Tables.Organization;
import com.example.Software_Advance.models.Tables.Volunteer;
import com.example.Software_Advance.repositories.OrgVolunteerRepository;
import com.example.Software_Advance.repositories.OrganizationRepository;
import com.example.Software_Advance.repositories.VolunteerRepository;
import com.example.Software_Advance.services.OrgVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/orgVolunteer")
public class OrgVolunteerController {

    @Autowired
    private OrgVolunteerRepository orgVolunteerRepository;
    @Autowired
    private OrgVolunteerService orgVolunteerService;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping("/all")
    public List<OrgVolunteer> getAllOrgVolunteers() {
        return orgVolunteerService.getAllOrgVolunteers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrgVolunteer> getOrgVolunteerById(@PathVariable Long id) {
        OrgVolunteer orgVolunteer = orgVolunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrgVolunteer not found with ID: " + id));
        return ResponseEntity.ok(orgVolunteer);
    }

    @GetMapping("/organization/{orgId}")
    public List<OrgVolunteer> getVolunteersByOrganization(@PathVariable Long orgId) {
        return orgVolunteerService.getVolunteersByOrganizationId(orgId);
    }

    @GetMapping("/volunteer/{volunteerId}")
    public List<OrgVolunteer> getOrganizationsByVolunteer(@PathVariable Long volunteerId) {
        return orgVolunteerService.getOrganizationsByVolunteerId(volunteerId);
    }

    @PutMapping("/updateSkills/{id}")
    public ResponseEntity<OrgVolunteer> updateSkills(@PathVariable Long id, @RequestBody String newSkills) {
        OrgVolunteer record = orgVolunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrgVolunteer not found with ID: " + id));

        record.setSkills(newSkills);

        if (record.getVolunteer() != null) {
            record.getVolunteer().setSkills(newSkills);
        }

        orgVolunteerRepository.save(record);
        return ResponseEntity.ok(record);
    }
    @PostMapping("/add")
    public ResponseEntity<OrgVolunteer> addOrgVolunteer(@RequestBody OrgVolunteerDto dto) {

        if (dto.getVolunteerId() == null) {
            throw new InvalidRequestException("Volunteer ID is required.");
        }

        Volunteer volunteer = volunteerRepository.findById(dto.getVolunteerId())
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with ID: " + dto.getVolunteerId()));

        Organization org = null;
        if (dto.getOrganizationId() != null) {
            org = organizationRepository.findById(dto.getOrganizationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Organization not found with ID: " + dto.getOrganizationId()));
        }

        OrgVolunteer newRecord = new OrgVolunteer();
        newRecord.setVolunteer(volunteer);
        newRecord.setOrganization(org);
        newRecord.setSkills(dto.getSkills());
        newRecord.setParticipationStatus(dto.getParticipationStatus());


        newRecord.getVolunteer().setOrganizationId(dto.getOrganizationId());
        newRecord.getVolunteer().setSkills(dto.getSkills());

        orgVolunteerRepository.save(newRecord);

        return ResponseEntity.status(HttpStatus.CREATED).body(newRecord);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<OrgVolunteer> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        ParticipationStatus st = ParticipationStatus.valueOf(body.get("participationStatus").toUpperCase());
        return ResponseEntity.ok(orgVolunteerService.updateParticipationStatus(id, st));
    }

}