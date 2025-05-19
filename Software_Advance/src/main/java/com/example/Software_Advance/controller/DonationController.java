package com.example.Software_Advance.controller;
import com.example.Software_Advance.dto.DonationDto;
import com.example.Software_Advance.dto.PaymentResponse;
import com.example.Software_Advance.externalApi.PaymentService;
import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Tables.Donation;
import com.example.Software_Advance.repositories.DonationRepository;
import com.example.Software_Advance.repositories.DonorRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.Software_Advance.services.*;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {
    @Autowired
    private DonationService donationService;
    @Autowired
    private DonorService donorService;
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DonationRepository donationRepository;
    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @GetMapping("/{donorId}")
    public ResponseEntity<List<DonationDto>> getDonationsByDonorId(@PathVariable Long donorId) {
        List<DonationDto> donationDtos = donationService.getDonationByDonorId(donorId);

        if (donationDtos.isEmpty()) {
            //return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(donationDtos);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<DonationDto>> filterDonations(
            @RequestParam(required = false) DonationType donationType,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount) {

        List<DonationDto> filteredDonations = donationService.filterDonations(donationType, minAmount, maxAmount);
        return ResponseEntity.ok(filteredDonations);
    }

    @PutMapping("/{donationId}") //http://localhost:8080/api/donations/4?donorId=4    example
    public ResponseEntity<String> updateDonation(@PathVariable Long donationId,
                                                 @RequestParam Long donorId,
                                                 @RequestBody DonationDto dto) {
        try {
            donationService.updateDonation(donationId, donorId, dto);
            return ResponseEntity.ok("Donation updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/donate")
    public ResponseEntity<?> createDonation(@RequestBody DonationDto dto,
                                            @RequestParam Long donorId) {
        try {
            Donation donation = donationService.convertDtoToDonation(dto, donorId);

            String paymentResult = donationService.processDonation(donation);

            if (paymentResult.startsWith("pi_") || paymentResult.contains("_secret_")) {
                return ResponseEntity.ok(new PaymentResponse(paymentResult));
            } else {
                return ResponseEntity.ok(paymentResult);
            }
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Payment processing failed: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{donationId}")
    public ResponseEntity<String> deleteDonation(@PathVariable Long donationId) {
        try {
            donationService.deleteDonation(donationId);
            return ResponseEntity.ok("Donation deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/totalAmount/{donorId}")
    public ResponseEntity<String> calculateAmount(@PathVariable Long donorId) {
        try {
            Double totalAmount = donationService.calculateTotalDonations(donorId);

            if (totalAmount == null || totalAmount == 0.0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No donations found for donor with ID " + donorId);
            }

            return ResponseEntity.ok("The total donations for donor with ID " + donorId + " is: " + totalAmount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while calculating the total donations.");
        }
    }
    @GetMapping("/payment-intent/{paymentIntentId}")
    public ResponseEntity<?> getPaymentStatus(@PathVariable String paymentIntentId) throws StripeException {
        PaymentIntent intent = PaymentIntent.retrieve(paymentIntentId);
        return ResponseEntity.ok(intent.getStatus());
    }
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload,
                                                @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event;
        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid signature");
        }

        switch (event.getType()) {
            case "payment_intent.succeeded":
            case "payment_intent.payment_failed":
            case "payment_intent.processing":
            case "payment_intent.requires_payment_method":
                PaymentIntent intent = (PaymentIntent) event.getDataObjectDeserializer()
                        .getObject().orElse(null);
                if (intent != null) {
                    Donation donation = donationRepository.findByPaymentIntentId(intent.getId());

                    if (donation != null) {
                        donation.setPaymentStatus(intent.getStatus());
                        donationRepository.save(donation);
                    }
                }
                break;
            default:
        }

        return ResponseEntity.ok("Received");
    }



}