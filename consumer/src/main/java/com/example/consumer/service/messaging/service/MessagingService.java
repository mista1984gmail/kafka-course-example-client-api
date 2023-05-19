package com.example.consumer.service.messaging.service;

import com.example.consumer.domain.dto.ClientDto;
import com.example.consumer.service.ClientService;
import com.example.consumer.service.messaging.event.ClientEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class MessagingService {
    private static final String topicCreateClient = "${topic.create-client}";
    private static final String topicCreateClientScheduler = "${topic.create-client-scheduler}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";


    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Transactional
    @KafkaListener(topics = topicCreateClient, groupId = kafkaConsumerGroupId, containerFactory = "clientContainerFactory")
    public ClientEvent clientCreateFromUI(ClientEvent clientEvent) {
        log.info("message consumed {}", clientEvent);
        clientService.save(modelMapper.map(clientEvent, ClientDto.class));
        return clientEvent;
    }

    @Transactional
    @KafkaListener(topics = topicCreateClientScheduler, groupId = kafkaConsumerGroupId, containerFactory = "clientContainerFactory")
    public ClientEvent clientCreateFromScheduler(ClientEvent clientEvent) {
        log.info("message consumed {}", clientEvent);
        clientService.save(modelMapper.map(clientEvent, ClientDto.class));
        return clientEvent;
    }
}
