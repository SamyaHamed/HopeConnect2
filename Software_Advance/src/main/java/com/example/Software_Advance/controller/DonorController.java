package com.example.Software_Advance.controller;
import com.example.Software_Advance.models.Tables.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Software_Advance.services.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/donors")
public class DonorController {
    @Autowired
    private DonorService donorService; // the object is injection in donorController without make object


    @GetMapping("{id}")
    public ResponseEntity<?> getDonorById(@PathVariable Long id) {
        Optional<Donor> donor = donorService.getDonorById(id);

        if (donor.isPresent()) {
            return ResponseEntity.ok(donor.get());
        } else {
            return ResponseEntity.ok("No donor found with the given ID.");
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDonorById(@PathVariable Long id){
        donorService.deleteDonor(id);
        return ResponseEntity.noContent().build();
    }

}