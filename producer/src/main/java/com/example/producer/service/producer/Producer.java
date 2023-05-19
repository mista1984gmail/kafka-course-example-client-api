package com.example.producer.service.producer;

import com.example.producer.domain.Client;
import com.example.producer.service.impl.CreateClientService;
import com.example.producer.service.messanging.service.TopicSendService;
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

    private final ObjectMapper objectMapper;
    private final CreateClientService clientService;
    private final TopicSendService topicSendService;

    public String sendMessage(Client client) throws JsonProcessingException {
       // String clientAsMessage = objectMapper.writeValueAsString(client);
        topicSendService.clientCreatedFromUI(client);

        log.info("Client produced {}", client);

        return "message sent";
    }


    @Scheduled(fixedRate = 30000)
    public void sendMessageOnScheduled() throws JsonProcessingException {

        //String clientAsMessage = objectMapper.writeValueAsString(clientService.createClient());
        Client client = clientService.createClient();
        topicSendService.clientCreatedFromScheduler(client);

        log.info("Client produced {}", client);

    }

}
