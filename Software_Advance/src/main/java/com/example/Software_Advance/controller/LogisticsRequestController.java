package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.LogisticsRequestDto;
import com.example.Software_Advance.models.Tables.LogisticsRequest;
import com.example.Software_Advance.services.LogisticsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logistics-requests")
public class LogisticsRequestController {

    @Autowired
    private LogisticsRequestService logisticsRequestService;

    @PostMapping("/create")
    public ResponseEntity<LogisticsRequest> createRequest(@RequestBody LogisticsRequestDto dto) {
        LogisticsRequest created = logisticsRequestService.createRequest(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LogisticsRequest>> getAllRequests() {
        return ResponseEntity.ok(logisticsRequestService.getAllRequests());
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<LogisticsRequest>> getRequestsByDonor(@PathVariable Long donorId) {
        return ResponseEntity.ok(logisticsRequestService.getRequestsByDonorId(donorId));
    }

    @GetMapping("/donor/{donorId}/latest")
    public ResponseEntity<List<LogisticsRequest>> getLatest5Requests(@PathVariable Long donorId) {
        return ResponseEntity.ok(logisticsRequestService.getLatest5RequestsForDonor(donorId));
    }
    @GetMapping("/orphanages/request-count")
    public ResponseEntity<Map<Long, Long>> getRequestCountPerOrphanage() {
        return ResponseEntity.ok(logisticsRequestService.countRequestsPerOrphanage());
    }

    @PutMapping("/{id}")
    public LogisticsRequest updateRequest(@PathVariable Long id, @RequestBody LogisticsRequestDto dto) {
        return logisticsRequestService.updateRequest(id, dto);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        logisticsRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
