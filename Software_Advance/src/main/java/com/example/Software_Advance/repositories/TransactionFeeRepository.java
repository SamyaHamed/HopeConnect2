/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.TransactionFee;
import com.example.Software_Advance.models.Enums.DonationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionFeeRepository extends JpaRepository<TransactionFee, Long> {

    List<TransactionFee> findByDonationId(Long donationId);

    List<TransactionFee> findByDonationType(DonationType donationType);

    List<TransactionFee> findByTransactionDateAfter(LocalDateTime transactionDate);

    List<TransactionFee> findByFeePercentage(Double feePercentage);
}*/