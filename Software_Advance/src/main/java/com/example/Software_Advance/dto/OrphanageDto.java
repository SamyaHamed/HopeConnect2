package com.example.Software_Advance.dto;


import lombok.Data;


@Data
public class OrphanageDto {
    private UserDto user;
    private int orphanCount;
    private boolean verified;
        private int capacity;

}