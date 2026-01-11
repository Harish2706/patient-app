package com.pm.analyticsservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumer {
    @KafkaListener(topics = "patient",groupId = "analytics-service")
    public void consumeEvent(byte[] event){

    }
}
