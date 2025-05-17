package com.example.Software_Advance.controller;
import com.example.Software_Advance.models.Enums.Availability;
import com.example.Software_Advance.models.Tables.Volunteer;
import com.example.Software_Advance.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin(origins = "*")
public class VolunteerController {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusBody) {
        Optional<Volunteer> optional = volunteerRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Volunteer volunteer = optional.get();
        String status = statusBody.get("status");
        volunteer.setStatus(status);
        volunteerRepository.save(volunteer);

        return ResponseEntity.ok("Status updated successfully.");
    }


    @PutMapping("/{id}/availability")
    public ResponseEntity<String> updateAvailability(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<Volunteer> optional = volunteerRepository.findById(id); // لازم يكون هون

        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Volunteer volunteer = optional.get();
        String availability = body.get("availability");

        try {
            Availability newAvailability = Availability.valueOf(availability.toUpperCase().trim());
            volunteer.setAvailability(newAvailability);
            volunteerRepository.save(volunteer);
            return ResponseEntity.ok("Availability updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid availability value.");
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Volunteer>> searchBySkills(@RequestParam String keyword) {
        List<Volunteer> volunteers = volunteerRepository.findBySkillsContaining(keyword);
        return ResponseEntity.ok(volunteers);
    }

    @GetMapping("/availability/{availability}")
    public ResponseEntity<List<Volunteer>> filterByAvailability(@PathVariable Availability availability) {
        List<Volunteer> volunteers = volunteerRepository.findByAvailability(availability);
        return ResponseEntity.ok(volunteers);
    }

    @PutMapping("/{id}/skills")
    public ResponseEntity<String> updateSkills(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<Volunteer> optional = volunteerRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Volunteer volunteer = optional.get();
        String newSkills = body.get("skills");
        volunteer.setSkills(newSkills);
        volunteerRepository.save(volunteer);

        return ResponseEntity.ok("Skills updated successfully.");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Volunteer>> getByStatus(@PathVariable String status) {
        List<Volunteer> volunteers = volunteerRepository.findByStatusIgnoreCase(status);
        return ResponseEntity.ok(volunteers);
    }
}

