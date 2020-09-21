package com.voxloud.kafka.producer;

import com.voxloud.kafka.message.avro.OrderCanceled;
import com.voxloud.kafka.message.avro.OrderCreated;
import com.voxloud.kafka.message.json.BaseEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class MessageProducer {

    @Autowired
    private KafkaTemplate<Long, BaseEvent> eventKafkaTemplate;

    @Autowired
    private KafkaTemplate<Long, OrderCreated> orderCreatedKafkaTemplate;

    @Autowired
    private KafkaTemplate<Long, OrderCanceled> orderCanceledKafkaTemplate;

    @Value(value = "${event.topic.name}")
    private String eventTopicName;

    @Value(value = "${order.topic.name}")
    private String orderTopicName;

    public void sendOrderCreated(Long id, OrderCreated orderCreated) {
        orderCreatedKafkaTemplate.send(orderTopicName, id, orderCreated);
    }

    public void sendOrderCanceled(Long id, OrderCanceled orderCanceled) {
        orderCanceledKafkaTemplate.send(orderTopicName, id, orderCanceled);
    }

    public void sendEvent(Integer partition, Long key, BaseEvent event) {
        eventKafkaTemplate.send(eventTopicName, partition, key, event);
    }
}