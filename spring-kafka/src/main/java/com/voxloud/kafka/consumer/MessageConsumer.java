package com.voxloud.kafka.consumer;

import com.voxloud.kafka.event.BaseEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.CountDownLatch;

public class MessageConsumer {

    public CountDownLatch latch = new CountDownLatch(3);

    public CountDownLatch partitionLatch = new CountDownLatch(2);

    public CountDownLatch filterLatch = new CountDownLatch(1);

    public CountDownLatch eventLatch = new CountDownLatch(1);

    public CountDownLatch orderLatch = new CountDownLatch(4);

    @KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group 'foo': " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "${message.topic.name}", groupId = "bar", containerFactory = "barKafkaListenerContainerFactory")
    public void listenGroupBar(String message) {
        System.out.println("Received Message in group 'bar': " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "${message.topic.name}", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload String message,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message: " + message + " from partition: " + partition);
        latch.countDown();
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${partitioned.topic.name}", partitions = {"0",
            "3"}), containerFactory = "partitionsKafkaListenerContainerFactory")
    public void listenToPartition(@Payload String message,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message: " + message + " from partition: " + partition);
        this.partitionLatch.countDown();
    }

    @KafkaListener(topics = "${filtered.topic.name}", containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        System.out.println("Received Message in filtered listener: " + message);
        this.filterLatch.countDown();
    }

    @KafkaListener(topics = "${event.topic.name}", containerFactory = "eventKafkaListenerContainerFactory")
    public void eventListener(BaseEvent event) {
        System.out.println("Received event: " + event);
        this.eventLatch.countDown();
    }

    @KafkaListener(topics = "${order.topic.name}", containerFactory = "orderKafkaListenerContainerFactory")
    public void orderListener(ConsumerRecord<?, ?> record) {
        System.out.println("Received order: " + record);
        this.orderLatch.countDown();
    }
}