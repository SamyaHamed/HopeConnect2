package com.example.Software_Advance.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class DonorDto {
    private UserDto user;
    private List<DonationDto> donations;
}

