package com.example.Software_Advance.services;

import com.example.Software_Advance.dto.DonationReportDTO;
import com.example.Software_Advance.dto.OrphanHealthSummaryDTO;
import com.example.Software_Advance.repositories.DonationRepository;
import com.example.Software_Advance.repositories.OrphanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {

    private final DonationRepository donationRepository;
    private final OrphanRepository orphanRepository;

    public ReportsService(DonationRepository donationRepository, OrphanRepository orphanRepository) {
        this.donationRepository = donationRepository;
        this.orphanRepository = orphanRepository;
    }

    public List<DonationReportDTO> getDonationReport() {
        return donationRepository.getDonationReportGroupedByType();
    }

    public List<OrphanHealthSummaryDTO> getOrphanHealthSummary() {
        return orphanRepository.getHealthSummaryReport();
    }
}
