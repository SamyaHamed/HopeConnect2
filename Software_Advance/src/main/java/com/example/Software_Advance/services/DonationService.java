package com.example.Software_Advance.services;

import com.example.Software_Advance.dto.DonationDto;
import com.example.Software_Advance.exceptions.BadRequestException;
import com.example.Software_Advance.exceptions.ResourceNotFoundException;
import com.example.Software_Advance.externalApi.PaymentService;
import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Tables.Donation;
import com.example.Software_Advance.models.Tables.Donor;
import com.example.Software_Advance.repositories.DonationRepository;
import com.example.Software_Advance.repositories.DonorRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private PaymentService paymentService;

    public Donation saveDonation(DonationDto dto, Long donorId) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with ID: " + donorId));

        Donation donation = new Donation();
        donation.setDonationType(dto.getDonationType());
        donation.setDonationAmount(dto.getDonationAmount());
        donation.setOrganizationId(dto.getOrganizationId());
        donation.setPaymentType(dto.getPaymentType());
        donation.setDonor(donor);

        return donationRepository.save(donation);
    }

    public List<DonationDto> getDonationByDonorId(Long id) {
        List<Donation> donations = donationRepository.findByDonorId(id);
        List<DonationDto> donationDtoList = new ArrayList<>();

        for (Donation donation : donations) {
            DonationDto dto = new DonationDto(
                    donation.getDonationType(),
                    donation.getDonationAmount(),
                    donation.getOrganizationId(),
                    donation.getPaymentType()
            );
            donationDtoList.add(dto);
        }

        return donationDtoList;
    }

    public void updateDonation(Long donationId, Long donorId, DonationDto dto) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with ID: " + donorId));

        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new ResourceNotFoundException("Donation not found with ID: " + donationId));

        if (!donation.getDonor().getId().equals(donorId)) {
            throw new BadRequestException("Donation does not belong to the specified donor");
        }

        if (dto.getDonationType() != null) {
            donation.setDonationType(dto.getDonationType());
        }
        if (dto.getDonationAmount() != null) {
            donation.setDonationAmount(dto.getDonationAmount());
        }
        if (dto.getPaymentType() != null) {
            donation.setPaymentType(dto.getPaymentType());
        }

        donationRepository.save(donation);
    }

    public void deleteDonation(Long donationId) {
        if (!donationRepository.existsById(donationId)) {
            throw new ResourceNotFoundException("Donation not found with ID: " + donationId);
        }
        donationRepository.deleteById(donationId);
    }

    public List<DonationDto> filterDonations(DonationType donationType, Double minAmount, Double maxAmount) {
        List<Donation> allDonations = donationRepository.findAll();
        List<DonationDto> filteredDonations = new ArrayList<>();

        for (Donation donation : allDonations) {
            boolean matchesType = (donationType == null || donation.getDonationType() == donationType);
            boolean matchesMin = (minAmount == null || donation.getDonationAmount() >= minAmount);
            boolean matchesMax = (maxAmount == null || donation.getDonationAmount() <= maxAmount);

            if (matchesType && matchesMin && matchesMax) {
                filteredDonations.add(new DonationDto(
                        donation.getDonationType(),
                        donation.getDonationAmount(),
                        donation.getOrganizationId(),
                        donation.getPaymentType()
                ));
            }
        }

        return filteredDonations;
    }

    public Double calculateTotalDonations(Long donorId) {
        List<Donation> donations = donationRepository.findByDonorId(donorId);
        if (donations == null || donations.isEmpty()) {
            return 0.0;
        }

        return donations.stream()
                .mapToDouble(Donation::getDonationAmount)
                .sum();
    }

    public String processDonation(Donation donation) throws StripeException {
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(donation);
        donation.setPaymentIntentId(paymentIntent.getId());
        donationRepository.save(donation);
        return paymentIntent.getClientSecret();
    }

    public Donation convertDtoToDonation(DonationDto dto, Long donorId) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with ID: " + donorId));

        Donation donation = new Donation();
        donation.setDonationType(dto.getDonationType());
        donation.setPaymentType(dto.getPaymentType());
        donation.setDonationAmount(dto.getDonationAmount());
        donation.setOrganizationId(dto.getOrganizationId());
        donation.setDonor(donor);

        return donation;
    }
}
