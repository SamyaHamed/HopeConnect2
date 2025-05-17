package com.example.Software_Advance.dto;


import com.example.Software_Advance.models.Enums.ServiceType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrganizationDto {
    private UserDto user;
    private ServiceType serviceType;
}