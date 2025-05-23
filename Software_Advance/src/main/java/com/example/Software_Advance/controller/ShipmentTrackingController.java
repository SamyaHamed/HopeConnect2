package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.ShipmentTrackingRequestDto;
import com.example.Software_Advance.dto.ShipmentTrackingUpdateDto;
import com.example.Software_Advance.models.Enums.ShipmentStatus;
import com.example.Software_Advance.models.Tables.ShipmentTracking;
import com.example.Software_Advance.services.ShipmentTrackingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shipment-tracking")
public class ShipmentTrackingController {

    @Autowired
    private ShipmentTrackingService shipmentTrackingService;

    @PostMapping("/create")
    public ResponseEntity<?> createShipmentTracking(@RequestBody ShipmentTrackingRequestDto dto) {
        try {
            ShipmentTracking shipmentTracking = shipmentTrackingService.createShipmentTracking(
                    dto.getLogisticsRequestId(), dto.getStatus(), dto.getCurrentLocation());
            return new ResponseEntity<>(shipmentTracking, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{shipmentId}")
    public ResponseEntity<?> updateShipmentTracking(
            @PathVariable Long shipmentId,
            @RequestBody ShipmentTrackingUpdateDto dto) {
        try {
            ShipmentTracking updatedShipment = shipmentTrackingService.updateShipmentTracking(
                    shipmentId, dto.getStatus(), dto.getCurrentLocation());
            return new ResponseEntity<>(updatedShipment, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{shipmentId}")
    public ResponseEntity<?> deleteShipmentTracking(@PathVariable Long shipmentId) {
        try {
            shipmentTrackingService.deleteShipmentTracking(shipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-logistics-request/{logisticsRequestId}")
    public ResponseEntity<?> getShipmentTrackingByLogisticsRequest(@PathVariable Long logisticsRequestId) {
        List<ShipmentTracking> shipmentTrackings = shipmentTrackingService.getShipmentTrackingByLogisticsRequest(logisticsRequestId);
        if (shipmentTrackings.isEmpty()) {
            return new ResponseEntity<>("No shipment tracking records found for logisticsRequestId: " + logisticsRequestId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shipmentTrackings, HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<?> getShipmentTrackingByStatus(@RequestParam ShipmentStatus status) {
        List<ShipmentTracking> shipmentTrackings = shipmentTrackingService.getShipmentTrackingByStatus(status);
        if (shipmentTrackings.isEmpty()) {
            return new ResponseEntity<>("No shipment tracking records found for status: " + status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shipmentTrackings, HttpStatus.OK);
    }

    @GetMapping("/check-status/{shipmentId}")
    public ResponseEntity<?> checkShipmentStatus(@PathVariable Long shipmentId) {
        try {
            ShipmentStatus status = shipmentTrackingService.checkShipmentStatus(shipmentId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-update-time-range")
    public ResponseEntity<?> getShipmentTrackingByUpdateTimeRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<ShipmentTracking> shipmentTrackings = shipmentTrackingService.getShipmentTrackingByUpdateTimeRange(startDate, endDate);
        if (shipmentTrackings.isEmpty()) {
            return new ResponseEntity<>("No shipment tracking records found in the given date range", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shipmentTrackings, HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> getShipmentStatistics() {
        return new ResponseEntity<>(shipmentTrackingService.getShipmentStatistics(), HttpStatus.OK);
    }

    @GetMapping("/is-delivered/{shipmentId}")
    public ResponseEntity<?> isShipmentDelivered(@PathVariable Long shipmentId) {
        try {
            boolean isDelivered = shipmentTrackingService.isShipmentDelivered(shipmentId);
            return new ResponseEntity<>(isDelivered, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
