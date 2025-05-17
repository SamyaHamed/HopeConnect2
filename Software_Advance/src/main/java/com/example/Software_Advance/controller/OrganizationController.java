package com.example.Software_Advance.controller;

import com.example.Software_Advance.models.Enums.ServiceType;
import com.example.Software_Advance.models.Tables.Organization;
import com.example.Software_Advance.repositories.OrganizationRepository;
import com.example.Software_Advance.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping("/service-type/{type}")
    public ResponseEntity<List<Organization>> getByServiceType(@PathVariable ServiceType type) {
        return ResponseEntity.ok(organizationService.getOrganizationsByServiceType(type));
    }

    @PutMapping("/{id}/service-type")
    public ResponseEntity<String> updateServiceType(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String typeStr = body.get("serviceType");
        organizationService.updateServiceType(id, typeStr);
        return ResponseEntity.ok("Service type updated successfully.");
    }





}