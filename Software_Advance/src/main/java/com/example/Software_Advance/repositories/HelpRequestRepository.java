package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Enums.HelpRequestStatus;
import com.example.Software_Advance.models.Tables.HelpRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HelpRequestRepository extends JpaRepository<HelpRequest, Long> {

    List<HelpRequest> findByOrphanageId(Long orphanageId);
    List<HelpRequest> findByStatus(HelpRequestStatus status);
    List<HelpRequest> findByTitle(String title);
    void deleteByOrphanageId(Long orphanageId);

}