package com.example.consumer.service.impl;

import com.example.consumer.domain.Client;
import com.example.consumer.domain.dto.ClientDto;
import com.example.consumer.repository.ClientRepository;
import com.example.consumer.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(ClientDto clientDto) {
        Client client =  modelMapper.map(clientDto, Client.class);
        Client savedClient = clientRepository.save(client);

        log.info("food order saved {}", savedClient);
    }
}
