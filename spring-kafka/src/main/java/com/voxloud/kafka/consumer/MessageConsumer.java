package com.voxloud.kafka.consumer;

import com.voxloud.kafka.message.json.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Slf4j
public class MessageConsumer {

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${event.topic.name}", partitions = {"0", "1", "2"}),
                   containerFactory = "eventKafkaListenerContainerFactory")
    public void eventListenerPartition(BaseEvent event, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws InterruptedException {
        log.info("Received event {} on partition {}", event, partition);
    }

    @KafkaListener(topics = "${order.topic.name}", containerFactory = "orderKafkaListenerContainerFactory")
    public void orderListener(ConsumerRecord<?, ?> order) {
        log.info("Received order {}", order.value());
    }
}