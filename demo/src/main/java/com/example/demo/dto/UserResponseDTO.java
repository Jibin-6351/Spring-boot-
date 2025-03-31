package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private LocalDate dob;
    private String countryCode;
    private String mobileNumber;
}
