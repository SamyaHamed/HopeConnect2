package com.example.Software_Advance.controller;
import com.example.Software_Advance.dto.ShipmentTrackingRequestDto;
import com.example.Software_Advance.dto.ShipmentTrackingUpdateDto;
import com.example.Software_Advance.models.Enums.ShipmentStatus;
import com.example.Software_Advance.models.Tables.ShipmentTracking;
import com.example.Software_Advance.services.ShipmentTrackingService;
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
    public ResponseEntity<ShipmentTracking> createShipmentTracking(@RequestBody ShipmentTrackingRequestDto dto) {
        try {
            ShipmentTracking shipmentTracking = shipmentTrackingService.createShipmentTracking(
                    dto.getLogisticsRequestId(), dto.getStatus(), dto.getCurrentLocation());
            return new ResponseEntity<>(shipmentTracking, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{shipmentId}")
    public ResponseEntity<ShipmentTracking> updateShipmentTracking(
            @PathVariable Long shipmentId,
            @RequestBody ShipmentTrackingUpdateDto dto) {
        try {
            ShipmentTracking updatedShipment = shipmentTrackingService.updateShipmentTracking(
                    shipmentId, dto.getStatus(), dto.getCurrentLocation());
            return new ResponseEntity<>(updatedShipment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // حذف سجل تتبع الشحنة
    @DeleteMapping("/delete/{shipmentId}")
    public ResponseEntity<Void> deleteShipmentTracking(@PathVariable Long shipmentId) {
        try {
            shipmentTrackingService.deleteShipmentTracking(shipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // الحصول على تتبع الشحنة حسب الـ Logistics Request
    @GetMapping("/by-logistics-request/{logisticsRequestId}")
    public ResponseEntity<List<ShipmentTracking>> getShipmentTrackingByLogisticsRequest(@PathVariable Long logisticsRequestId) {
        List<ShipmentTracking> shipmentTrackings = shipmentTrackingService.getShipmentTrackingByLogisticsRequest(logisticsRequestId);
        if (shipmentTrackings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shipmentTrackings, HttpStatus.OK);
    }

    // الحصول على الشحنات في حالة معينة
    @GetMapping("/by-status")
    public ResponseEntity<List<ShipmentTracking>> getShipmentTrackingByStatus(@RequestParam ShipmentStatus status) {
        List<ShipmentTracking> shipmentTrackings = shipmentTrackingService.getShipmentTrackingByStatus(status);
        if (shipmentTrackings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shipmentTrackings, HttpStatus.OK);
    }

    // التحقق من حالة الشحنة
    @GetMapping("/check-status/{shipmentId}")
    public ResponseEntity<ShipmentStatus> checkShipmentStatus(@PathVariable Long shipmentId) {
        try {
            ShipmentStatus status = shipmentTrackingService.checkShipmentStatus(shipmentId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // الشحنات التي تم تحديثها خلال نطاق زمني
    @GetMapping("/by-update-time-range")
    public ResponseEntity<List<ShipmentTracking>> getShipmentTrackingByUpdateTimeRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<ShipmentTracking> shipmentTrackings = shipmentTrackingService.getShipmentTrackingByUpdateTimeRange(startDate, endDate);
        if (shipmentTrackings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shipmentTrackings, HttpStatus.OK);
    }

    // إحصائيات الشحنات حسب الحالة
    @GetMapping("/statistics")
    public ResponseEntity<?> getShipmentStatistics() {
        return new ResponseEntity<>(shipmentTrackingService.getShipmentStatistics(), HttpStatus.OK);
    }

    // هل الشحنة تم تسليمها؟
    @GetMapping("/is-delivered/{shipmentId}")
    public ResponseEntity<Boolean> isShipmentDelivered(@PathVariable Long shipmentId) {
        boolean isDelivered = shipmentTrackingService.isShipmentDelivered(shipmentId);
        return new ResponseEntity<>(isDelivered, HttpStatus.OK);
    }
}
