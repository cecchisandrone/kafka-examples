package com.voxloud.kafka;

import com.example.order.OrderCreatedValue;
import com.example.payment.PaymentCreatedValue;
import com.voxloud.kafka.controller.OrderCreatedDto;
import com.voxloud.kafka.controller.PaymentCreatedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageChannel orderCreatedSource;

    @Autowired
    private MessageChannel paymentCreatedSource;

    @PostMapping("/order")
    public void sendOrderCreatedMessage(@RequestBody OrderCreatedDto orderCreatedDto) {
        OrderCreatedValue orderCreatedValue =
                OrderCreatedValue.newBuilder().setOrderId(orderCreatedDto.getOrderId()).setAmount(orderCreatedDto.getAmount())
                                 .setRequestId(orderCreatedDto.getRequestId()).setItemId(orderCreatedDto.getItemId()).build();

        orderCreatedSource
                .send(MessageBuilder.withPayload(orderCreatedValue).setHeader(KafkaHeaders.MESSAGE_KEY, orderCreatedValue.getRequestId()).build());
        System.out.println("OrderCreatedValue message sent");
    }

    @PostMapping("/payment")
    public void sendPaymentCreatedMessage(@RequestBody PaymentCreatedDto paymentCreatedDto) {
        PaymentCreatedValue paymentCreatedValue =
                PaymentCreatedValue.newBuilder().setPaymentId(paymentCreatedDto.getPaymentId()).setRequestId(paymentCreatedDto.getRequestId())
                                   .setType(paymentCreatedDto.getType()).build();

        paymentCreatedSource
                .send(MessageBuilder.withPayload(paymentCreatedValue).setHeader(KafkaHeaders.MESSAGE_KEY, paymentCreatedValue.getRequestId())
                                    .build());
        System.out.println("PaymentCreatedValue message sent");
    }
}
