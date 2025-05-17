package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.OrgVolunteerDto;
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
        return orgVolunteerService.getAllOrgVolunteers();  // هذه دالة ترجع جميع البيانات
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrgVolunteer> getOrgVolunteerById(@PathVariable Long id) {
        Optional<OrgVolunteer> orgVolunteer = orgVolunteerRepository.findById(id);
        if (orgVolunteer.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(orgVolunteer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
        Optional<OrgVolunteer> record = orgVolunteerRepository.findById(id);
        if (record.isPresent()) {
            OrgVolunteer ov = record.get();
            ov.setSkills(newSkills);

            if (ov.getVolunteer() != null) {
                ov.getVolunteer().setSkills(newSkills);
            }

            orgVolunteerRepository.save(ov);
            return ResponseEntity.ok(ov);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrgVolunteer(@RequestBody OrgVolunteerDto dto) {

        Optional<Volunteer> volunteerOpt = volunteerRepository.findById(dto.getVolunteerId());
        if (!volunteerOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Volunteer not found with ID: " + dto.getVolunteerId());
        }
        Volunteer volunteer = volunteerOpt.get();

        Organization org = null;
        if (dto.getOrganizationId() != null) {
            Optional<Organization> orgOpt = organizationRepository.findById(dto.getOrganizationId());
            if (!orgOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Organization not found with ID: " + dto.getOrganizationId());
            }
            org = orgOpt.get();
        }

        OrgVolunteer newRecord = new OrgVolunteer();
        newRecord.setVolunteer(volunteer);
        newRecord.setOrganization(org);
        newRecord.setSkills(dto.getSkills());

        newRecord.getVolunteer().setOrganizationId(dto.getOrganizationId());

        newRecord.getVolunteer().setSkills(dto.getSkills());
        orgVolunteerRepository.save(newRecord);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newRecord);
    }



}
