package com.example.consumer.service.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEvent {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
}
