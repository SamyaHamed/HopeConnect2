package com.example.Software_Advance.controller;

import com.example.Software_Advance.models.Tables.ReliefCampaign;
import com.example.Software_Advance.services.ReliefCampaignService;
import com.example.Software_Advance.repositories.ReliefCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/relief-campaigns")
public class ReliefCampaignController {

    @Autowired
    private ReliefCampaignService reliefCampaignService;

    @PostMapping
    public ResponseEntity<ReliefCampaign> createCampaign(@RequestBody ReliefCampaign campaign) {
        return ResponseEntity.ok(reliefCampaignService.createCampaign(campaign));
    }

    @GetMapping
    public ResponseEntity<List<ReliefCampaign>> getAllCampaigns() {
        return ResponseEntity.ok(reliefCampaignService.getAllCampaigns());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReliefCampaign> getCampaignById(@PathVariable Long id) {
        return reliefCampaignService.getCampaignById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReliefCampaign> updateCampaign(@PathVariable Long id,
                                                         @RequestBody ReliefCampaign campaign) {
        return ResponseEntity.ok(reliefCampaignService.updateCampaign(id, campaign));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        reliefCampaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/active")
    public ResponseEntity<List<ReliefCampaign>> getActiveCampaigns() {
        return ResponseEntity.ok(reliefCampaignService.getActiveCampaigns());
    }

    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<ReliefCampaign>> getCampaignsByOrg(@PathVariable Long orgId) {
        return ResponseEntity.ok(
                reliefCampaignService.getCampaignsByOrganization(orgId)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReliefCampaign>> searchCampaigns(@RequestParam String keyword) {
        List<ReliefCampaign> campaigns = reliefCampaignService.searchCampaigns(keyword);
        return ResponseEntity.ok(campaigns);
    }
}