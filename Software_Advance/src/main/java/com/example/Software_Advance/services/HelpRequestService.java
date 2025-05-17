package com.example.Software_Advance.services;

import com.example.Software_Advance.dto.HelpRequestDto;
import com.example.Software_Advance.exceptions.NoHelpRequestsFoundException;
import com.example.Software_Advance.models.Enums.HelpRequestStatus;
import com.example.Software_Advance.models.Tables.HelpRequest;
import com.example.Software_Advance.models.Tables.Orphanage;
import com.example.Software_Advance.repositories.HelpRequestRepository;
import com.example.Software_Advance.repositories.OrphanageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpRequestService {

    @Autowired
    private HelpRequestRepository helpRequestRepository;
    @Autowired
    private OrphanageRepository orphanageRepository;

    @Autowired

    public List<HelpRequest> getAllHelpRequests() {
        return helpRequestRepository.findAll();
    }

    public Optional<HelpRequest> getHelpRequestById(Long id) {
        return helpRequestRepository.findById(id);
    }


    public HelpRequest addHelpRequest(HelpRequestDto helpRequestDTO) {
        Orphanage orphanage = orphanageRepository.findById(helpRequestDTO.getOrphanageId())
                .orElseThrow(() -> new RuntimeException("Orphanage with ID " + helpRequestDTO.getOrphanageId() + " not found"));


        HelpRequest helpRequest = new HelpRequest();
        helpRequest.setTitle(helpRequestDTO.getTitle());
        helpRequest.setDescription(helpRequestDTO.getDescription());
        helpRequest.setStatus(helpRequestDTO.getStatus());
        helpRequest.setOrphanage(orphanage);

        return helpRequestRepository.save(helpRequest);
    }

    public List<HelpRequest> getHelpRequestsByOrphanageId(Long orphanageId) {
        List<HelpRequest> results = helpRequestRepository.findByOrphanageId(orphanageId);
        if (results.isEmpty()) {
            throw new NoHelpRequestsFoundException("No help requests found for orphanage ID: " + orphanageId);
        }
        return results;
    }

    public List<HelpRequest> getHelpRequestsByStatus(HelpRequestStatus status) {
        List<HelpRequest> results = helpRequestRepository.findByStatus(status);
        if (results.isEmpty()) {
            throw new NoHelpRequestsFoundException("No help requests found with status: " + status);
        }
        return results;
    }

    public List<HelpRequest> getHelpRequestsByTitle(String title) {
        List<HelpRequest> results = helpRequestRepository.findByTitle(title);
        if (results.isEmpty()) {
            throw new NoHelpRequestsFoundException("No help requests found with title: " + title);
        }
        return results;
    }
    public HelpRequest updateHelpRequest(Long id, HelpRequest updatedHelpRequest) {
        return helpRequestRepository.findById(id).map(existing -> {
            existing.setTitle(updatedHelpRequest.getTitle());
            existing.setDescription(updatedHelpRequest.getDescription());
            existing.setStatus(updatedHelpRequest.getStatus());
            return helpRequestRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("HelpRequest not found with id: " + id));
    }

    public boolean deleteHelpRequest(Long id) {
        if (!helpRequestRepository.existsById(id)) {
            return false;
        }
        helpRequestRepository.deleteById(id);
        return true;
    }

    public HelpRequest findHelpRequestById(Long id) {
        return helpRequestRepository.findById(id).orElse(null);
    }

    public HelpRequest save(HelpRequest helpRequest) {
        return helpRequestRepository.save(helpRequest);
    }




}
