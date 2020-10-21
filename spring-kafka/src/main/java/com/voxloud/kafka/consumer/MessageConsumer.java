package com.voxloud.kafka.consumer;

import com.voxloud.kafka.message.json.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Slf4j
public class MessageConsumer {

    @KafkaListener(topics = "${event.topic.name}", containerFactory = "baseEventKafkaListenerContainerFactory")
    public void eventListenerPartition(BaseEvent event, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Received event {} on partition {}", event, partition);
    }

    @KafkaListener(topics = "${order.topic.name}", containerFactory = "orderKafkaListenerContainerFactory")
    public void orderListener(ConsumerRecord<?, ?> order, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Received order {} on partition {}", order.value(), partition);
    }
}