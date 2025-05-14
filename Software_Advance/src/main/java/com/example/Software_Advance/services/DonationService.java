package com.example.Software_Advance.services;
import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Tables.Donation;
import com.example.Software_Advance.models.Tables.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Software_Advance.repositories.*;
import com.example.Software_Advance.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private DonorRepository donorRepository;

    public Donation saveDonation(DonationDTO dto, Long donorId) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        Donation donation = new Donation();
        donation.setDonationType(dto.getDonationType());
        donation.setDonationAmount(dto.getDonationAmount());
        donation.setOrganizationId(dto.getOrganizationId());
        donation.setPaymentType(dto.getPaymentType());

        donation.setDonor(donor);
        return donationRepository.save(donation);
    }

    public List<DonationDTO> getDonationByDonorId(Long id){
        List<Donation> donations = donationRepository.findByDonorId(id);
        List<DonationDTO> donationDTOList = new ArrayList<>();

        for (Donation donation : donations) {
            DonationDTO dto = new DonationDTO(
                    donation.getDonationType(),
                    donation.getDonationAmount(),
                    donation.getOrganizationId(),
                    donation.getPaymentType()
            );
            donationDTOList.add(dto);
        }

        return donationDTOList;
    }
    // update specific field
    public void updateDonation(Long donationId, Long donorId, DonationDTO dto) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        if (!donation.getDonor().getId().equals(donorId)) {
            throw new RuntimeException("Donation does not belong to the specified donor");
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

        // Save the updated donation to the repository
        donationRepository.save(donation);
    }
    public void deleteDonation (Long donationId){
        if (donationRepository.existsById(donationId)) {
            donationRepository.deleteById(donationId);
        } else {
            throw new RuntimeException("Donation not found with ID: " + donationId);
        }
    }
    public List<DonationDTO> filterDonations(DonationType donationType, Double minAmount, Double maxAmount){
        List<Donation> allDonations = donationRepository.findAll();
        List<DonationDTO> filteredDonations = new ArrayList<>();

        for (Donation donation : allDonations) {
            boolean matchesType = (donationType == null || donation.getDonationType() == donationType);
            boolean matchesMin = (minAmount == null || donation.getDonationAmount() >= minAmount);
            boolean matchesMax = (maxAmount == null || donation.getDonationAmount() <= maxAmount);

            if (matchesType && matchesMin && matchesMax) {
                filteredDonations.add(new DonationDTO(
                        donation.getDonationType(),
                        donation.getDonationAmount(),
                        donation.getOrganizationId(),
                        donation.getPaymentType()
                ));
            }
        }

        return filteredDonations;
    }

    public Double calculateTotalDonations (Long donorId){
        Double totalAmount = 0.0 ;
        List<Donation> donations = donationRepository.findByDonorId(donorId);
        if (donations == null || donations.isEmpty()) {
            return 0.0;
        }
        for(Donation donation : donations){
            totalAmount+=donation.getDonationAmount();
        }
        return totalAmount;
    }


}
