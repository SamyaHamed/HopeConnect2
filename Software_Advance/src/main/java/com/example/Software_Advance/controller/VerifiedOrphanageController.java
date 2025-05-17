package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.OrphanageWithVerificationDto;
import com.example.Software_Advance.models.Enums.VerificationStatus;
import com.example.Software_Advance.models.Tables.VerifiedOrphanage;
import com.example.Software_Advance.services.VerifiedOrphanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/verified-orphanages")
public class VerifiedOrphanageController {

    @Autowired
    private VerifiedOrphanageService verifiedOrphanageService;

    @PostMapping("/verify")
    public ResponseEntity<VerifiedOrphanage> verifyOrphanage(@RequestBody OrphanageWithVerificationDto request) {
        VerifiedOrphanage verifiedOrphanage = verifiedOrphanageService.saveVerification(
                request.getOrphanageId(),
                request.getAdminId(),
                request.getStatus()
        );
        return new ResponseEntity<>(verifiedOrphanage, HttpStatus.CREATED);
    }


    @GetMapping("/admin/{adminId}")
    public ResponseEntity<?> getByAdmin(@PathVariable Long adminId) {
        List<VerifiedOrphanage> list = verifiedOrphanageService.getByAdminId(adminId);
        return ResponseEntity.ok(list);
    }

    // استرجاع دار الأيتام بناءً على حالة التوثيق
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getByStatus(@PathVariable VerificationStatus status) {
        List<VerifiedOrphanage> list = verifiedOrphanageService.getByStatus(status);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getByDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date); // Format: yyyy-MM-dd
            List<VerifiedOrphanage> list = verifiedOrphanageService.getByVerificationDate(localDate);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format. Expected yyyy-MM-dd.");
        }
    }

    @GetMapping("/admin/{adminId}/status/{status}")
    public ResponseEntity<?> getByAdminAndStatus(@PathVariable Long adminId, @PathVariable VerificationStatus status) {
        List<VerifiedOrphanage> list = verifiedOrphanageService.getByAdminAndStatus(adminId, status);
        return ResponseEntity.ok(list);
    }

    // استرجاع أحدث توثيق لدار أيتام معينة
    @GetMapping("/latest/{orphanageId}")
    public ResponseEntity<?> getLatest(@PathVariable Long orphanageId) {
        Optional<VerifiedOrphanage> result = verifiedOrphanageService.getLatestVerification(orphanageId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/latest/{orphanageId}/status/{status}")
    public ResponseEntity<?> getLatestWithStatus(@PathVariable Long orphanageId, @PathVariable VerificationStatus status) {
        Optional<VerifiedOrphanage> result = verifiedOrphanageService.getLatestVerified(orphanageId, status);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
