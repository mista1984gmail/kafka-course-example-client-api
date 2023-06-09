package com.example.consumer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
}

