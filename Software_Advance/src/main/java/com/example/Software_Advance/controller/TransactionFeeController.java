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

        TransactionFee created = transactionFeeService.createTransactionFee(fee, dto.getDonationId());
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<TransactionFee>> getAllFees() {
        return ResponseEntity.ok(transactionFeeService.getAllFees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionFee> getFeeById(@PathVariable Long id) {
        return transactionFeeService.getFeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/donation/{donationId}")
    public ResponseEntity<List<TransactionFee>> getByDonation(@PathVariable Long donationId) {
        return ResponseEntity.ok(transactionFeeService.getFeesByDonationId(donationId));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<TransactionFee>> getByType(@PathVariable DonationType type) {
        return ResponseEntity.ok(transactionFeeService.getFeesByType(type));
    }

    @GetMapping("/after-date")
    public ResponseEntity<List<TransactionFee>> getByDate(@RequestParam("date") String dateStr) {
        LocalDateTime date = LocalDateTime.parse(dateStr);
        return ResponseEntity.ok(transactionFeeService.getFeesAfterDate(date));
    }

    @GetMapping("/percentage/{percentage}")
    public ResponseEntity<List<TransactionFee>> getByPercentage(@PathVariable Double percentage) {
        return ResponseEntity.ok(transactionFeeService.getFeesByPercentage(percentage));
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
