package com.example.Software_Advance.dto;

import lombok.Data;

import java.util.List;

@Data
public class DonorDto {
    private UserDto user;
    private List<DonationDto> donations;
}

