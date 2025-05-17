package com.example.Software_Advance.services;

import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Tables.Donor;
import com.example.Software_Advance.models.Tables.TransactionFee;
import com.example.Software_Advance.repositories.TransactionFeeRepository;
import com.example.Software_Advance.repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionFeeService {

    private final TransactionFeeRepository transactionFeeRepository;
    @Autowired
    private DonorRepository donorRepository;

    public TransactionFee createTransactionFee(TransactionFee transactionFee, Long donorId) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found with ID: " + donorId));
        transactionFee.setDonation(donor);
        return transactionFeeRepository.save(transactionFee);
    }
    public TransactionFeeService(TransactionFeeRepository transactionFeeRepository) {
        this.transactionFeeRepository = transactionFeeRepository;
    }

    public List<TransactionFee> getAllFees() {
        return transactionFeeRepository.findAll();
    }

    public Optional<TransactionFee> getFeeById(Long id) {
        return transactionFeeRepository.findById(id);
    }

    public List<TransactionFee> getFeesByDonationId(Long donationId) {
        return transactionFeeRepository.findByDonationId(donationId);
    }

    public List<TransactionFee> getFeesByType(DonationType type) {
        return transactionFeeRepository.findByDonationType(type);
    }

    public List<TransactionFee> getFeesAfterDate(LocalDateTime date) {
        return transactionFeeRepository.findByTransactionDateAfter(date);
    }

    public List<TransactionFee> getFeesByPercentage(Double percentage) {
        return transactionFeeRepository.findByFeePercentage(percentage);
    }

    public TransactionFee saveFee(TransactionFee fee) {
        return transactionFeeRepository.save(fee);
    }

    public void deleteFee(Long id) {
        transactionFeeRepository.deleteById(id);
    }

    public Double getTotalFeesForDonor(Long donorId) {
        Double total = transactionFeeRepository.getTotalFeesByDonorId(donorId);
        return total != null ? total : 0.0;
    }

}
