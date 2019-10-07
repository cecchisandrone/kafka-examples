package com.voxloud.kafka;

import com.example.order.OrderCreatedValue;
import com.example.payment.PaymentCreatedValue;
import com.voxloud.kafka.config.KafkaConfig;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @StreamListener(KafkaConfig.OrderCreatedBinding.ORDER_CREATED_SINK)
    public void handle(@Payload OrderCreatedValue orderCreatedValue, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        System.out.println("Received: " + orderCreatedValue + " with key: " + key);
        if (orderCreatedValue.getAmount() == 0) {
            throw new RuntimeException("Amount is 0");
        }
    }

    @StreamListener(KafkaConfig.PaymentCreatedBinding.PAYMENT_CREATED_SINK)
    public void handle(@Payload PaymentCreatedValue paymentCreatedValue, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        System.out.println("Received: " + paymentCreatedValue + " with key: " + key);
    }

    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("Handling ERROR: " + message);
    }
}
