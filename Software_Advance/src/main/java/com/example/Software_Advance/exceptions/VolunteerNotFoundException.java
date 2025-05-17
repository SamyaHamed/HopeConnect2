package com.example.Software_Advance.exceptions;

public class VolunteerNotFoundException extends RuntimeException {
    public VolunteerNotFoundException(Long id) {
        super("Volunteer with ID " + id + " not found.");
    }
}
