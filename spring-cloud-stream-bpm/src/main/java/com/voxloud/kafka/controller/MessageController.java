package com.voxloud.kafka.controller;

import com.example.order.OrderCreatedValue;
import com.example.payment.PaymentCreatedValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
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
        log.info("OrderCreatedValue message sent");
    }

    @PostMapping("/payment")
    public void sendPaymentCreatedMessage(@RequestBody PaymentCreatedDto paymentCreatedDto) {
        PaymentCreatedValue paymentCreatedValue =
                PaymentCreatedValue.newBuilder().setPaymentId(paymentCreatedDto.getPaymentId()).setRequestId(paymentCreatedDto.getRequestId())
                                   .setType(paymentCreatedDto.getType()).build();

        paymentCreatedSource
                .send(MessageBuilder.withPayload(paymentCreatedValue).setHeader(KafkaHeaders.MESSAGE_KEY, paymentCreatedValue.getRequestId())
                                    .build());
        log.info("PaymentCreatedValue message sent");
    }
}
