package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.DonationReportDto;
import com.example.Software_Advance.dto.OrphanHealthSummaryDto;
import com.example.Software_Advance.services.ReportsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getDonationReport() {
        try {
            List<DonationReportDto> report = reportService.getDonationReport();
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve donation report: " + e.getMessage());
        }
    }

    @GetMapping("/orphans/health-summary")
    public ResponseEntity<?> getOrphanHealthSummary() {
        try {
            List<OrphanHealthSummaryDto> summary = reportService.getOrphanHealthSummary();
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve orphan health summary: " + e.getMessage());
        }
    }
}
