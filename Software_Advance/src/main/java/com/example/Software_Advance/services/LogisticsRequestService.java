package com.example.Software_Advance.services;

import com.example.Software_Advance.dto.LogisticsRequestDto;
import com.example.Software_Advance.models.Tables.*;
import com.example.Software_Advance.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LogisticsRequestService {

    @Autowired
    private LogisticsRequestRepository repository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private OrphanageRepository orphanageRepository;

    public LogisticsRequest createRequest(LogisticsRequestDto dto) {
        Donor donor = donorRepository.findById(dto.getDonorId())
                .orElseThrow(() -> new RuntimeException("Donor not found"));
        Orphanage orphanage = orphanageRepository.findById(dto.getOrphanageId())
                .orElseThrow(() -> new RuntimeException("Orphanage not found"));

        LogisticsRequest request = new LogisticsRequest();
        request.setDonor(donor);
        request.setOrphanage(orphanage);
        request.setDonationType(dto.getDonationType());
        request.setQuantity(dto.getQuantity());
        request.setPickupLocation(dto.getPickupLocation());
        request.setDeliveryLocation(dto.getDeliveryLocation());
        request.setStatus(dto.getStatus());
        request.setRequestDate(new Date());

        return repository.save(request);
    }

    public List<LogisticsRequest> getAllRequests() {
        return repository.findAll();
    }

    public List<LogisticsRequest> getRequestsByDonorId(Long donorId) {
        return repository.findByDonorId(donorId);
    }

    public List<LogisticsRequest> getLatest5RequestsForDonor(Long donorId) {
        return repository.findTop5ByDonorIdOrderByRequestDateDesc(donorId);
    }
    public Map<Long, Long> countRequestsPerOrphanage() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        req -> req.getOrphanage().getId(),
                        Collectors.counting()
                ));
    }
    public LogisticsRequest updateRequest(Long id, LogisticsRequestDto dto) {
        Donor donor = donorRepository.findById(dto.getDonorId())
                .orElseThrow(() -> new RuntimeException("Donor not found"));
        Orphanage orphanage = orphanageRepository.findById(dto.getOrphanageId())
                .orElseThrow(() -> new RuntimeException("Orphanage not found"));

        return repository.findById(id)
                .map(request -> {
                    request.setDonor(donor);
                    request.setOrphanage(orphanage);
                    request.setDonationType(dto.getDonationType());
                    request.setQuantity(dto.getQuantity());
                    request.setPickupLocation(dto.getPickupLocation());
                    request.setDeliveryLocation(dto.getDeliveryLocation());
                    request.setStatus(dto.getStatus());
                    request.setRequestDate(new Date());
                    return repository.save(request);
                })
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }


    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}
