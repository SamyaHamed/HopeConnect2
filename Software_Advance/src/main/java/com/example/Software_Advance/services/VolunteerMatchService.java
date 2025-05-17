package com.example.Software_Advance.services;

import com.example.Software_Advance.dto.VolunteerMatchDto;
import com.example.Software_Advance.exceptions.DuplicateUserException;
import com.example.Software_Advance.exceptions.ResourceNotFoundException;
import com.example.Software_Advance.models.Enums.HelpRequestStatus;
import com.example.Software_Advance.models.Tables.HelpRequest;
import com.example.Software_Advance.models.Tables.Volunteer;
import com.example.Software_Advance.models.Tables.VolunteerMatch;
import com.example.Software_Advance.repositories.HelpRequestRepository;
import com.example.Software_Advance.repositories.VolunteerMatchRepository;
import com.example.Software_Advance.repositories.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerMatchService {

    private final VolunteerMatchRepository volunteerMatchRepository;
    private final VolunteerRepository volunteerRepository;
    private final HelpRequestRepository requestRepository;

    public VolunteerMatchDto createMatch(Long volunteerId, Long requestId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with ID: " + volunteerId));
        HelpRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Help request not found with ID: " + requestId));

        volunteerMatchRepository.findByVolunteerIdAndRequestId(volunteerId, requestId)
                .ifPresent(existing -> {
                    throw new DuplicateUserException("Match already exists for volunteer ID " + volunteerId + " and request ID " + requestId);
                });

        boolean isMatch = isSkillsMatch(volunteer.getSkills(), request.getTitle(), request.getDescription(), request.getStatus());

        VolunteerMatch match = new VolunteerMatch();
        match.setVolunteer(volunteer);
        match.setRequest(request);
        match.setMatchStatus(isMatch);
        match.setMatchedAt(LocalDateTime.now());

        VolunteerMatch saved = volunteerMatchRepository.save(match);

        return toDTO(saved);
    }



    private boolean isSkillsMatch(String skills, String title, String description, HelpRequestStatus requestStatus) {
        if (requestStatus == HelpRequestStatus.CANCELLED || requestStatus == HelpRequestStatus.COMPLETED) {
            return false;
        }

        if (skills == null || title == null || description == null) return false;

        String allText = (title + " " + description).toLowerCase();
        String[] skillWords = skills.toLowerCase().split(",\\s*");

        for (String skill : skillWords) {
            if (allText.contains(skill.trim())) {
                return true;
            }
        }
        return false;
    }


    public List<VolunteerMatchDto> getAllMatches() {
        return volunteerMatchRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteMatch(Long id) {
        VolunteerMatch match = volunteerMatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with ID: " + id));
        volunteerMatchRepository.delete(match);
    }


    private VolunteerMatchDto toDTO(VolunteerMatch match) {
        VolunteerMatchDto dto = new VolunteerMatchDto();
        dto.setId(match.getId());
        dto.setVolunteerId(match.getVolunteer().getId());
        dto.setRequestId(match.getRequest().getId());
        dto.setMatchStatus(match.getMatchStatus());
        dto.setMatchedAt(match.getMatchedAt());
        return dto;
    }

    public VolunteerMatch updateMatchStatus(Long matchId, VolunteerMatchDto dto) {
        VolunteerMatch match = volunteerMatchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with ID: " + matchId));

        if (dto.getMatchStatus() != null) {
            match.setMatchStatus(dto.getMatchStatus());
        }

        return volunteerMatchRepository.save(match);
    }

    public VolunteerMatch updateMatchDate(Long matchId, VolunteerMatchDto dto) {
        VolunteerMatch match = volunteerMatchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with ID: " + matchId));

        if (dto.getMatchedAt() != null) {
            match.setMatchedAt(dto.getMatchedAt());
        }

        return volunteerMatchRepository.save(match);
    }


}
