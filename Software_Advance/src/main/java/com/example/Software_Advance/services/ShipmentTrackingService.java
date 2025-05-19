package com.example.Software_Advance.services;

import com.example.Software_Advance.models.Tables.LogisticsRequest;
import com.example.Software_Advance.models.Tables.ShipmentTracking;
import com.example.Software_Advance.models.Enums.ShipmentStatus;
import com.example.Software_Advance.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShipmentTrackingService {
    @Autowired
    private ShipmentTrackingRepository shipmentTrackingRepository;
    @Autowired
    private LogisticsRequestRepository logisticsRequestRepository;

    public ShipmentTracking createShipmentTracking(Long logisticsRequestId, ShipmentStatus status, String currentLocation) {
        LogisticsRequest logisticsRequest = logisticsRequestRepository.findById(logisticsRequestId)
                .orElseThrow(() -> new RuntimeException("LogisticsRequest not found"));

        ShipmentTracking shipmentTracking = new ShipmentTracking();
        shipmentTracking.setLogisticsRequest(logisticsRequest);  // ربط LogisticsRequest
        shipmentTracking.setStatus(status);
        shipmentTracking.setCurrentLocation(currentLocation);
        shipmentTracking.setUpdateTime(LocalDateTime.now());

        return shipmentTrackingRepository.save(shipmentTracking);
    }

    public ShipmentTracking updateShipmentTracking(Long shipmentId, ShipmentStatus status, String currentLocation) {
        ShipmentTracking shipmentTracking = shipmentTrackingRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("ShipmentTracking not found"));

        if (status != null) {
            shipmentTracking.setStatus(status);
        }

        if (currentLocation != null) {
            shipmentTracking.setCurrentLocation(currentLocation);
        }

        shipmentTracking.setUpdateTime(LocalDateTime.now());

        return shipmentTrackingRepository.save(shipmentTracking);
    }

    public void deleteShipmentTracking(Long shipmentId) {
        ShipmentTracking shipmentTracking = shipmentTrackingRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("ShipmentTracking not found"));
        shipmentTrackingRepository.delete(shipmentTracking);
    }

    public List<ShipmentTracking> getShipmentTrackingByLogisticsRequest(Long logisticsRequestId) {
        return shipmentTrackingRepository.findByLogisticsRequestId(logisticsRequestId);
    }

    public List<ShipmentTracking> getShipmentTrackingByStatus(ShipmentStatus status) {
        return shipmentTrackingRepository.findByStatus(status);
    }

    public ShipmentStatus checkShipmentStatus(Long shipmentId) {
        ShipmentTracking shipmentTracking = shipmentTrackingRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("ShipmentTracking not found"));

        return shipmentTracking.getStatus();
    }

    public List<ShipmentTracking> getShipmentTrackingByUpdateTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
        return shipmentTrackingRepository.findByUpdateTimeBetween(startDate, endDate);
    }

    public Map<ShipmentStatus, Long> getShipmentStatistics() {
        return shipmentTrackingRepository.findAll().stream()
                .collect(Collectors.groupingBy(ShipmentTracking::getStatus, Collectors.counting()));
    }

    public boolean isShipmentDelivered(Long shipmentId) {
        ShipmentTracking shipmentTracking = shipmentTrackingRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("ShipmentTracking not found"));

        return shipmentTracking.getStatus() == ShipmentStatus.DELIVERED;
    }
}
