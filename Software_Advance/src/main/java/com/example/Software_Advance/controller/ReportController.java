package com.example.Software_Advance.controller;


import com.example.Software_Advance.dto.DonationReportDto;
import com.example.Software_Advance.dto.OrphanHealthSummaryDto;
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
    public List<DonationReportDto> getDonationReport() {
        return reportService.getDonationReport();
    }

    @GetMapping("/orphans/health-summary")
    public List<OrphanHealthSummaryDto> getOrphanHealthSummary() {
        return reportService.getOrphanHealthSummary();
    }
}
