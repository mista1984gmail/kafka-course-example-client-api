package com.example.consumer.service.consumer;

import com.example.consumer.domain.dto.ClientDto;
import com.example.consumer.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {
    private static final String topicCreateClient = "${topic.create-client}";
    private static final String topicCreateClientScheduler = "${topic.create-client-scheduler}";

    private final ObjectMapper objectMapper;
    private final ClientService clientService;


    @KafkaListener(topics = topicCreateClient)
    public void consumeMessage(String message) throws JsonProcessingException {
        log.info("message consumed {}", message);

        ClientDto clientDto = objectMapper.readValue(message, ClientDto.class);
        clientService.save(clientDto);
    }

    @KafkaListener(topics = topicCreateClientScheduler)
    public void consumeMessageOnScheduler(String message) throws JsonProcessingException {
        log.info("message consumed {}", message);

        ClientDto clientDto = objectMapper.readValue(message, ClientDto.class);
        clientService.save(clientDto);
    }
}
