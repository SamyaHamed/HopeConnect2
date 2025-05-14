package com.example.Software_Advance.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class OrphanageDTO {
    private UserDTO user;
    private int orphanCount;
    private boolean verified;
        private int capacity;

}