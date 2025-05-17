package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Tables.Donation;
import com.example.Software_Advance.models.Enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonationType(DonationType donationType);

    List<Donation> findByPaymentType(PaymentType paymentType);

    List<Donation> findByOrganizationId(Long organizationId);

    List<Donation> findByDonorId(Long donorId);

    List<Donation> findByDonationAmountGreaterThanEqual(Double amount);
}