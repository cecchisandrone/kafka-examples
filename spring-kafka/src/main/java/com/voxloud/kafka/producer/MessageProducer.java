package com.voxloud.kafka.producer;

import com.voxloud.kafka.event.BaseEvent;
import com.voxloud.kafka.event.OrderCanceled;
import com.voxloud.kafka.event.OrderCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, BaseEvent> eventKafkaTemplate;

    @Autowired
    private KafkaTemplate<Long, OrderCreated> orderCreatedKafkaTemplate;

    @Autowired
    private KafkaTemplate<Long, OrderCanceled> orderCanceledKafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${partitioned.topic.name}")
    private String partionedTopicName;

    @Value(value = "${filtered.topic.name}")
    private String filteredTopicName;

    @Value(value = "${event.topic.name}")
    private String eventTopicName;

    @Value(value = "${order.topic.name}")
    private String orderTopicName;

    public void sendMessage(String message) {
        kafkaTemplate.send(topicName, message);
    }

    public void sendMessageToPartion(String message, int partition) {
        kafkaTemplate.send(partionedTopicName, partition, null, message);
    }

    public void sendMessageToFiltered(String message) {
        kafkaTemplate.send(filteredTopicName, message);
    }

    public void sendEvent(BaseEvent event) {
        eventKafkaTemplate.send(eventTopicName, event);
    }

    public void sendOrderCreated(long id, OrderCreated orderCreated) {
        orderCreatedKafkaTemplate.send(orderTopicName, id, orderCreated);
    }

    public void sendOrderCanceled(long id, OrderCanceled orderCanceled) {
        orderCanceledKafkaTemplate.send(orderTopicName, id, orderCanceled);
    }
}