package com.example.producer.service.producer;

import com.example.producer.domain.Client;
import com.example.producer.service.impl.CreateClientService;
import com.example.producer.service.messanging.event.ClientEvent;
import com.example.producer.service.messanging.service.TopicSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final CreateClientService clientService;
    private final TopicSendService topicSendService;
    private final ModelMapper modelMapper;

    public String sendMessage(Client client) {
        topicSendService.clientCreatedFromUI(modelMapper.map(client, ClientEvent.class));

        log.info("Client produced {}", client);

        return "message sent";
    }


    @Scheduled(fixedRate = 30000)
    public void sendMessageOnScheduled() {

        Client client = clientService.createClient();
        topicSendService.clientCreatedFromScheduler(modelMapper.map(client, ClientEvent.class));

        log.info("Client produced {}", client);

    }

}
