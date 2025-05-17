package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.UserRole;
import com.example.Software_Advance.models.Enums.UserType;
import com.example.Software_Advance.models.Tables.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {
    private String name;
    private String password;
    private String email;
    private String address;
    private String phone;
    private UserType type;
    private UserRole role;

    public static UserDto fromEntity(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setPhone(user.getPhone());
        dto.setType(user.getType());
        dto.setRole(user.getRole());
        return dto;
    }
}