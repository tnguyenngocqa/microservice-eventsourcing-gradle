package com.ltfullstack.notificationservice.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ComponentScan({"com.ltfullstack.notificationservice", "com.ltfullstack.commonservice"})
public class EventConsumer {

    @KafkaListener(topics = "test",containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message){
        log.info("Received message: {}", message);
    }
}
