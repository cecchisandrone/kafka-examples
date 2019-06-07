package com.voxloud.kafka;

import com.voxloud.kafka.consumer.MessageConsumer;
import com.voxloud.kafka.event.BaseEvent;
import com.voxloud.kafka.producer.MessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageConsumer listener = context.getBean(MessageConsumer.class);
        /*
         * Sending a Hello World message to topic 'message'. Must be received by
         * both listeners with group foo and bar with containerFactory
         * fooKafkaListenerContainerFactory and barKafkaListenerContainerFactory
         * respectively. It will also be received by the listener with
         * headersKafkaListenerContainerFactory as container factory
         */
        producer.sendMessage("Hello, World!");
        listener.latch.await(10, TimeUnit.SECONDS);

        Assert.isTrue(listener.latch.getCount() == 0, "Not all consumer groups received the message 'Hello World'");

        /*
         * Sending message to a topic with 5 partition, each message to a
         * different partition. But as per listener configuration, only the
         * messages from partition 0 and 3 will be consumed.
         */
        for (int i = 0; i < 5; i++) {
            producer.sendMessageToPartion("Hello To Partioned Topic!", i);
        }
        listener.partitionLatch.await(10, TimeUnit.SECONDS);

        Assert.isTrue(listener.partitionLatch.getCount() == 0, "Expected 2 messages to be read on selected partitions");

        /*
         * Sending message to 'filtered' topic. As per listener configuration,
         * all messages with char sequence 'World' will be discarded.
         */
        producer.sendMessageToFiltered("Hello Baeldung!");
        producer.sendMessageToFiltered("Hello World!");
        listener.filterLatch.await(10, TimeUnit.SECONDS);
        Assert.isTrue(listener.filterLatch.getCount() == 0, "Expected 1 messages to be read with filtered listener");

        /*
         * Sending message to 'event' topic. This will send and received a
         * java object with the help of eventKafkaListenerContainerFactory.
         */
        producer.sendEvent(new BaseEvent(new Date(), "user_created"));
        listener.eventLatch.await(10, TimeUnit.SECONDS);
        Assert.isTrue(listener.eventLatch.getCount() == 0, "A java object is expected to be received");

        context.close();
    }
}
