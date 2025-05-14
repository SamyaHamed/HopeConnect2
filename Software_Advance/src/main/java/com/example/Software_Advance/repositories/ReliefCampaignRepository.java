/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.ReliefCampaign;
import com.example.Software_Advance.models.Enums.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReliefCampaignRepository extends JpaRepository<ReliefCampaign, Long> {

    List<ReliefCampaign> findByOrganisationId(Long organisationId);

    List<ReliefCampaign> findByStatus(CampaignStatus status);

    List<ReliefCampaign> findByStartDateAfter(Date startDate);

    List<ReliefCampaign> findByEndDateBefore(Date endDate);

    List<ReliefCampaign> findByEndDateAfter(Date currentDate);

    List<ReliefCampaign> findByCollectedAmountGreaterThan(Double targetAmount);
}*/