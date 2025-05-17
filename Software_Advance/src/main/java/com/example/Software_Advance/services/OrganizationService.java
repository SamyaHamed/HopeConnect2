package com.example.Software_Advance.services;

import com.example.Software_Advance.exceptions.ResourceNotFoundException;
import com.example.Software_Advance.models.Enums.ServiceType;
import com.example.Software_Advance.models.Tables.Organization;
import com.example.Software_Advance.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with ID: " + id));
    }

    public List<Organization> getOrganizationsByServiceType(ServiceType type) {
        return organizationRepository.findByServiceType(type);
    }

    public Organization updateServiceType(Long id, String serviceTypeStr) {
        Organization org = getOrganizationById(id);
        try {
            ServiceType serviceTypeEnum = ServiceType.valueOf(serviceTypeStr.toUpperCase().trim());
            org.setServiceType(serviceTypeEnum);
            return organizationRepository.save(org);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid service type value: " + serviceTypeStr);
        }
    }

    public Organization saveOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public void deleteOrganization(Long id) {
        if (!organizationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Organization not found with ID: " + id);
        }
        organizationRepository.deleteById(id);
    }
}
