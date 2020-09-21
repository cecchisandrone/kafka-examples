package com.voxloud.kafka.controller;

import java.util.Date;

import com.voxloud.kafka.message.avro.OrderCanceled;
import com.voxloud.kafka.message.avro.OrderCreated;
import com.voxloud.kafka.producer.MessageProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvroMessageController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/order-created")
    public void sendOrderCreated(@RequestParam(name = "id") Long id) {
        OrderCreated orderCreated = OrderCreated.newBuilder().setCustomerId("1").setOrderId(id).setPrice(13).setItems(1).build();
        messageProducer.sendOrderCreated(orderCreated.getOrderId(), orderCreated);
    }

    @PostMapping("/order-canceled")
    public void sendOrderCanceled(@RequestParam(name = "id") Long id) {
        OrderCanceled orderCanceled = OrderCanceled.newBuilder().setOrderId(id).setTimestamp(new Date().getTime()).build();
        messageProducer.sendOrderCanceled(orderCanceled.getOrderId(), orderCanceled);
    }

}
