package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.HelpRequestDto;
import com.example.Software_Advance.models.Enums.HelpRequestStatus;
import com.example.Software_Advance.models.Tables.HelpRequest;
import com.example.Software_Advance.services.HelpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/help-requests")
public class HelpRequestController {

    @Autowired
    private HelpRequestService helpRequestService;

    @GetMapping
    public List<HelpRequest> getAllHelpRequests() {
        return helpRequestService.getAllHelpRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HelpRequest> getHelpRequestById(@PathVariable Long id) {
        return helpRequestService.getHelpRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/orphanage/{orphanageId}")
    public List<HelpRequest> getHelpRequestsByOrphanageId(@PathVariable Long orphanageId) {
        return helpRequestService.getHelpRequestsByOrphanageId(orphanageId);
    }

    @GetMapping("/status/{status}")
    public List<HelpRequest> getHelpRequestsByStatus(@PathVariable HelpRequestStatus status) {
        return helpRequestService.getHelpRequestsByStatus(status);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<HelpRequest>> getHelpRequestsByTitle(@PathVariable String title) {
        try {
            List<HelpRequest> helpRequests = helpRequestService.getHelpRequestsByTitle(title);

            if (helpRequests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }

            return ResponseEntity.ok(helpRequests);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping
    public ResponseEntity<HelpRequest> addHelpRequest(@RequestBody HelpRequestDto helpRequestDTO) {
        try {
            HelpRequest helpRequest = helpRequestService.addHelpRequest(helpRequestDTO);
            return new ResponseEntity<>(helpRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateHelpRequestStatus(@PathVariable Long id, @RequestBody HelpRequestStatus status) {
        try {
            HelpRequest existingHelpRequest = helpRequestService.findHelpRequestById(id);

            if (existingHelpRequest == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("HelpRequest with ID " + id + " not found");
            }

            existingHelpRequest.setStatus(status);

            HelpRequest updatedHelpRequest = helpRequestService.save(existingHelpRequest);

            return ResponseEntity.ok(updatedHelpRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<?> updateHelpRequestDescription(@PathVariable Long id, @RequestBody String description) {
        try {
            HelpRequest existingHelpRequest = helpRequestService.findHelpRequestById(id);

            if (existingHelpRequest == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("HelpRequest with ID " + id + " not found");
            }

            existingHelpRequest.setDescription(description);

            HelpRequest updatedHelpRequest = helpRequestService.save(existingHelpRequest);

            return ResponseEntity.ok(updatedHelpRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the description: " + e.getMessage());
        }
    }


    @PutMapping("/{id}/title")
    public ResponseEntity<?> updateHelpRequestTitle(@PathVariable Long id, @RequestBody String title) {
        try {
            HelpRequest existingHelpRequest = helpRequestService.findHelpRequestById(id);

            if (existingHelpRequest == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("HelpRequest with ID " + id + " not found");
            }

            existingHelpRequest.setTitle(title);

            HelpRequest updatedHelpRequest = helpRequestService.save(existingHelpRequest);

            return ResponseEntity.ok(updatedHelpRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the title: " + e.getMessage());
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHelpRequest(@PathVariable Long id) {
        try {
            boolean isDeleted = helpRequestService.deleteHelpRequest(id);

            if (!isDeleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("HelpRequest with ID " + id + " not found");
            }

            return ResponseEntity.ok("Delete Success");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the HelpRequest: " + e.getMessage());
        }
    }


}
