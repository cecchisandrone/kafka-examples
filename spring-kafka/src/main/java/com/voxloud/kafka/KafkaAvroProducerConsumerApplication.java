package com.voxloud.kafka;

import com.voxloud.kafka.consumer.MessageConsumer;
import com.voxloud.kafka.event.OrderCanceled;
import com.voxloud.kafka.event.OrderCreated;
import com.voxloud.kafka.producer.MessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaAvroProducerConsumerApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaAvroProducerConsumerApplication.class, args);
        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageConsumer listener = context.getBean(MessageConsumer.class);

        OrderCreated orderCreated = OrderCreated.newBuilder().setCustomerId("1").setOrderId("1").setPrice(13).setItems(1).build();
        producer.sendOrderCreated(1, orderCreated);
        orderCreated = OrderCreated.newBuilder().setCustomerId("1").setOrderId("2").setPrice(13).setItems(1).build();
        producer.sendOrderCreated(2, orderCreated);
        OrderCanceled orderCanceled = OrderCanceled.newBuilder().setOrderId("1").setTimestamp(new Date().getTime()).build();
        producer.sendOrderCanceled(1, orderCanceled);
        orderCanceled = OrderCanceled.newBuilder().setOrderId("2").setTimestamp(new Date().getTime()).build();
        producer.sendOrderCanceled(2, orderCanceled);

        listener.orderLatch.await(10, TimeUnit.SECONDS);
        Assert.isTrue(listener.orderLatch.getCount() == 0, "A java object is expected to be received");
        context.close();
    }
}
