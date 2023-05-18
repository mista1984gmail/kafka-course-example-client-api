package com.example.producer.service.producer;

import com.example.producer.domain.Client;
import com.example.producer.service.impl.CreateClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    @Value("${topic.create-client-scheduler}")
    private String topicCreateClientScheduler;

    @Value("${topic.create-client}")
    private String topicCreateClient;


    private final ObjectMapper objectMapper;
    private final CreateClientService clientService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(Client client) throws JsonProcessingException {
        String clientAsMessage = objectMapper.writeValueAsString(client);
        kafkaTemplate.send(topicCreateClient, clientAsMessage);

        log.info("Client produced {}", clientAsMessage);

        return "message sent";
    }


    @Scheduled(fixedRate = 30000)
    public void sendMessageOnScheduled() throws JsonProcessingException {

        String clientAsMessage = objectMapper.writeValueAsString(clientService.createClient());
        kafkaTemplate.send(topicCreateClientScheduler, clientAsMessage);

        log.info("Client produced {}", clientAsMessage);

    }

}
