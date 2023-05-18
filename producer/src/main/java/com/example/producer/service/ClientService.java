package com.example.producer.service;

import com.example.producer.domain.Client;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ClientService {
    String createClient(Client client) throws JsonProcessingException;
}
