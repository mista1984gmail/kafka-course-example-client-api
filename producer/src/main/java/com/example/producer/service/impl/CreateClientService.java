package com.example.producer.service.impl;

import com.example.producer.domain.Client;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CreateClientService {
    private Faker faker = new Faker();

    public Client createClient(){
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        return new Client(
                firstName,
                lastName,
                faker.address().fullAddress(),
                firstName.toLowerCase()+lastName.toLowerCase()+faker.number().numberBetween(100,999)+"@gmail.com"
        );
    }
}
