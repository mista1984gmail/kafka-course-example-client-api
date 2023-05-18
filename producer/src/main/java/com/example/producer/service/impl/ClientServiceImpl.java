package com.example.producer.service.impl;

import com.example.producer.domain.Client;
import com.example.producer.service.ClientService;

import com.example.producer.service.producer.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    private final Producer producer;

    @Autowired
    public ClientServiceImpl(Producer producer) {
        this.producer = producer;
    }


    @Override
    public String createClient(Client client) throws JsonProcessingException {
        return producer.sendMessage(client);
    }
}
