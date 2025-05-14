/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.ShipmentTracking;
import com.example.Software_Advance.models.Enums.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShipmentTrackingRepository extends JpaRepository<ShipmentTracking, Long> {

    List<ShipmentTracking> findByLogisticsRequestId(Long logisticsRequestId);

    List<ShipmentTracking> findByStatus(ShipmentStatus status);

    List<ShipmentTracking> findByUpdateTimeAfter(LocalDateTime updateTime);

    List<ShipmentTracking> findByCurrentLocation(String currentLocation);
}*/