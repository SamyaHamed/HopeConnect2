package com.example.Software_Advance.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrphanageDto {
    private UserDto user;
    private int orphanCount;
    private boolean verified;
    private int capacity;

}