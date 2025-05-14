package com.example.Software_Advance.services;

import com.example.Software_Advance.dto.DonationReportDto;
import com.example.Software_Advance.dto.OrphanHealthSummaryDto;
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

    public List<DonationReportDto> getDonationReport() {
        return donationRepository.getDonationReportGroupedByType();
    }

    public List<OrphanHealthSummaryDto> getOrphanHealthSummary() {
        return orphanRepository.getHealthSummaryReport();
    }
}
