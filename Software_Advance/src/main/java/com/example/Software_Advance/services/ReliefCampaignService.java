package com.example.Software_Advance.services;

import com.example.Software_Advance.models.Tables.Organization;
import com.example.Software_Advance.models.Tables.ReliefCampaign;
import com.example.Software_Advance.repositories.ReliefCampaignRepository;
import com.example.Software_Advance.repositories.OrganizationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReliefCampaignService {

    @Autowired
    private ReliefCampaignRepository reliefCampaignRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public List<ReliefCampaign> searchCampaigns(String keyword) {
        return reliefCampaignRepository.searchCampaignsByKeyword(keyword);
    }

    public ReliefCampaign createCampaign(ReliefCampaign campaign) {
        Long orgId = campaign.getOrganisation().getId();

        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found with ID: " + orgId));

        campaign.setOrganisation(org);
        return reliefCampaignRepository.save(campaign);
    }

    public List<ReliefCampaign> getAllCampaigns() {
        return reliefCampaignRepository.findAll();
    }

    public Optional<ReliefCampaign> getCampaignById(Long id) {
        return reliefCampaignRepository.findById(id);
    }

    public void deleteCampaign(Long id) {
        ReliefCampaign campaign = reliefCampaignRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found with id: " + id));
        reliefCampaignRepository.delete(campaign);
    }

    public ReliefCampaign updateCampaign(Long id, ReliefCampaign updatedCampaign) {
        return reliefCampaignRepository.findById(id).map(campaign -> {
            campaign.setTitle(updatedCampaign.getTitle());
            campaign.setDescription(updatedCampaign.getDescription());
            campaign.setTargetAmount(updatedCampaign.getTargetAmount());
            campaign.setCollectedAmount(updatedCampaign.getCollectedAmount());
            campaign.setStatus(updatedCampaign.getStatus());
            campaign.setStartDate(updatedCampaign.getStartDate());
            campaign.setEndDate(updatedCampaign.getEndDate());
            return reliefCampaignRepository.save(campaign);
        }).orElseThrow(() -> new EntityNotFoundException("Campaign not found with id: " + id));
    }

    public List<ReliefCampaign> getActiveCampaigns() {
        Date now = new Date();
        return reliefCampaignRepository.findByEndDateAfter(now);
    }

    public List<ReliefCampaign> getCampaignsByOrganization(Long orgId) {
        return reliefCampaignRepository.findByOrganisationId(orgId);
    }
}
