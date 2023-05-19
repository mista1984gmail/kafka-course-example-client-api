package com.example.producer.service.messanging.service;

import com.example.producer.service.messanging.event.ClientEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicSendService {

    @Value("${topic.create-client}")
    private String topicCreateClient;
    @Value("${topic.create-client-scheduler}")
    private String topicCreateClientScheduler;

    @Autowired
    private KafkaTemplate<String , Object> kafkaTemplate;

    public void clientCreatedFromUI(ClientEvent clientEvent) {
        this.kafkaTemplate.send(topicCreateClient, clientEvent);
    }
    public void clientCreatedFromScheduler(ClientEvent clientEvent) {
        this.kafkaTemplate.send(topicCreateClientScheduler, clientEvent);
    }
}
