package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.VolunteerMatchDto;
import com.example.Software_Advance.models.Tables.VolunteerMatch;
import com.example.Software_Advance.services.VolunteerMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class VolunteerMatchController {

    private final VolunteerMatchService matchService;

    @PostMapping
    public ResponseEntity<VolunteerMatchDto> createMatch(@RequestBody VolunteerMatchDto dto) {
        VolunteerMatchDto createdMatch = matchService.createMatch(dto.getVolunteerId(), dto.getRequestId());
        return ResponseEntity.ok(createdMatch);
    }

    @GetMapping
    public ResponseEntity<List<VolunteerMatchDto>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<VolunteerMatchDto> updateMatchStatus(
            @PathVariable Long id,
            @RequestBody VolunteerMatchDto updateDTO) {

        VolunteerMatch updatedMatch = matchService.updateMatchStatus(id, updateDTO);

        VolunteerMatchDto responseDTO = new VolunteerMatchDto();
        responseDTO.setId(updatedMatch.getId());
        responseDTO.setVolunteerId(updatedMatch.getVolunteer().getId());
        responseDTO.setRequestId(updatedMatch.getRequest().getId());
        responseDTO.setMatchStatus(updatedMatch.getMatchStatus());
        responseDTO.setMatchedAt(updatedMatch.getMatchedAt());

        return ResponseEntity.ok(responseDTO);
    }


    @PutMapping("/{id}/date")
    public ResponseEntity<VolunteerMatchDto> updateMatchDate(
            @PathVariable Long id,
            @RequestBody VolunteerMatchDto updateDTO) {

        VolunteerMatch updatedMatch = matchService.updateMatchDate(id, updateDTO);

        VolunteerMatchDto responseDTO = new VolunteerMatchDto();
        responseDTO.setId(updatedMatch.getId());
        responseDTO.setVolunteerId(updatedMatch.getVolunteer().getId());
        responseDTO.setRequestId(updatedMatch.getRequest().getId());
        responseDTO.setMatchStatus(updatedMatch.getMatchStatus());
        responseDTO.setMatchedAt(updatedMatch.getMatchedAt());

        return ResponseEntity.ok(responseDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok("Delete Success");
    }
}
