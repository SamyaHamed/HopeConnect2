package com.example.Software_Advance.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class DonorDTO {
    private UserDTO user;
    private List<DonationDTO> donations;
}

