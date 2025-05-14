/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.LogisticsRequest;
import com.example.Software_Advance.models.Enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticsRequestRepository extends JpaRepository<LogisticsRequest, Long> {

    List<LogisticsRequest> findByStatus(RequestStatus status);

    List<LogisticsRequest> findByDonorId(Long donorId);

    List<LogisticsRequest> findByOrphanageId(Long orphanageId);
}*/