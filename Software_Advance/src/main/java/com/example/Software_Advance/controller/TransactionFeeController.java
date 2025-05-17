package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.TransactionFeeDto;
import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Tables.TransactionFee;
import com.example.Software_Advance.services.TransactionFeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transaction-fees")
public class TransactionFeeController {

    private final TransactionFeeService transactionFeeService;

    public TransactionFeeController(TransactionFeeService transactionFeeService) {
        this.transactionFeeService = transactionFeeService;
    }

    @PostMapping
    public ResponseEntity<TransactionFee> createFee(@RequestBody TransactionFeeDto dto) {
        TransactionFee fee = new TransactionFee();
        fee.setFeeAmount(dto.getFeeAmount());
        fee.setFeePercentage(dto.getFeePercentage());
        fee.setTransactionDate(dto.getTransactionDate());
        fee.setDonationType(dto.getDonationType());

        return ResponseEntity.ok(
                transactionFeeService.createTransactionFee(fee, dto.getDonationId())
        );
    }

    @GetMapping
    public List<TransactionFee> getAllFees() {
        return transactionFeeService.getAllFees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionFee> getFeeById(@PathVariable Long id) {
        return transactionFeeService.getFeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/donation/{donationId}")
    public List<TransactionFee> getByDonation(@PathVariable Long donationId) {
        return transactionFeeService.getFeesByDonationId(donationId);
    }

    @GetMapping("/type/{type}")
    public List<TransactionFee> getByType(@PathVariable DonationType type) {
        return transactionFeeService.getFeesByType(type);
    }

    @GetMapping("/after-date")
    public List<TransactionFee> getByDate(@RequestParam("date") String dateStr) {
        LocalDateTime date = LocalDateTime.parse(dateStr);
        return transactionFeeService.getFeesAfterDate(date);
    }

    @GetMapping("/percentage/{percentage}")
    public List<TransactionFee> getByPercentage(@PathVariable Double percentage) {
        return transactionFeeService.getFeesByPercentage(percentage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFee(@PathVariable Long id) {
        transactionFeeService.deleteFee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-fees/donor/{donorId}")
    public ResponseEntity<Double> getTotalFeesByDonor(@PathVariable Long donorId) {
        Double total = transactionFeeService.getTotalFeesForDonor(donorId);
        return ResponseEntity.ok(total);
    }
}
