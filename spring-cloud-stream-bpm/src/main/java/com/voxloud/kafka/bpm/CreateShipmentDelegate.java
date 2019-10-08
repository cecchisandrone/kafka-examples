package com.voxloud.kafka.bpm;

import com.example.order.OrderCreatedValue;
import com.example.payment.PaymentCreatedValue;
import com.example.shipment.ShipmentCreatedValue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class CreateShipmentDelegate implements JavaDelegate {

    @Autowired
    private MessageChannel shipmentCreatedSource;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        OrderCreatedValue orderCreatedValue = (OrderCreatedValue) execution.getVariable("orderCreatedValue");
        PaymentCreatedValue paymentCreatedValue = (PaymentCreatedValue) execution.getVariable("paymentCreatedValue");
        ShipmentCreatedValue shipmentCreatedValue = ShipmentCreatedValue.newBuilder().setCourier("TNT").setItem(orderCreatedValue.getItemId())
                                                                        .setPaymentType(paymentCreatedValue.getType())
                                                                        .setRequestId(orderCreatedValue.getRequestId()
                                                                        ).setOrderId(orderCreatedValue.getOrderId()).build();
        shipmentCreatedSource
                .send(MessageBuilder.withPayload(shipmentCreatedValue).setHeader(KafkaHeaders.MESSAGE_KEY, orderCreatedValue.getRequestId().toString()
                ).build());
        logger.info("Created shipment: {}", paymentCreatedValue);
    }
}