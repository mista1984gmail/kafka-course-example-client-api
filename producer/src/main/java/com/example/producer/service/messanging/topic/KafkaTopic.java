package com.example.producer.service.messanging.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Value("${topic.create-client}")
    private String topicCreateClient;
    @Value("${topic.create-client-scheduler}")
    private String topicCreateClientScheduler;

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(topicCreateClient)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicScheduler() {
        return TopicBuilder
                .name(topicCreateClientScheduler)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
