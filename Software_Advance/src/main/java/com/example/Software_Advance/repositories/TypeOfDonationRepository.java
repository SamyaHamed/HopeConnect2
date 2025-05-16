/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.TypeOfDonation;
import com.example.Software_Advance.models.Enums.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TypeOfDonationRepository extends JpaRepository<TypeOfDonation, Long> {

    List<TypeOfDonation> findByCampaignId(Long campaignId);

    List<TypeOfDonation> findByUserId(Long userId);

    List<TypeOfDonation> findByDonationDateAfter(Date donationDate);

    List<TypeOfDonation> findByStatus(DonationStatus status);
    List<TypeOfDonation> findByUserIdAndCampaignId(Long userId, Long campaignId);
}*/