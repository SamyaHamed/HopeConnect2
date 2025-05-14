package com.example.Software_Advance.dto;


import com.example.Software_Advance.models.Enums.ServiceType;
import lombok.Data;

@Data
public class OrganizationDTO {
    private UserDTO user;
    private ServiceType serviceType;
}
