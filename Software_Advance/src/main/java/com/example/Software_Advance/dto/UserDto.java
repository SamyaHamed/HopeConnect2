package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.UserRole;
import com.example.Software_Advance.models.Enums.UserType;
import lombok.Data;

@Data

public class UserDto {
    private String name;
    private String password;
    private String email;
    private String address;
    private String phone;
    private UserType type;
    private UserRole role;


}
