package com.example.Software_Advance.controller;


import com.example.Software_Advance.dto.DonationReportDTO;
import com.example.Software_Advance.dto.OrphanHealthSummaryDTO;
import com.example.Software_Advance.services.ReportsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportsService reportService;

    public ReportController(ReportsService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/donations")
    public List<DonationReportDTO> getDonationReport() {
        return reportService.getDonationReport();
    }

    @GetMapping("/orphans/health-summary")
    public List<OrphanHealthSummaryDTO> getOrphanHealthSummary() {
        return reportService.getOrphanHealthSummary();
    }
}
