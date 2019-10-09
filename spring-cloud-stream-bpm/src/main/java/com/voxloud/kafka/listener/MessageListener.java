package com.voxloud.kafka.listener;

import com.example.order.OrderCreatedValue;
import com.example.payment.PaymentCreatedValue;
import com.voxloud.kafka.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {

    @Autowired
    private ProcessEngine processEngine;

    @StreamListener(KafkaConfig.OrderCreatedBinding.ORDER_CREATED_SINK)
    public void handle(@Payload OrderCreatedValue orderCreatedValue, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        log.info("Received: " + orderCreatedValue + " with key: " + key);

        MessageCorrelationResult messageCorrelationResult = processEngine.getRuntimeService()
                                                                         .createMessageCorrelation(orderCreatedValue.getClass().getSimpleName())
                                                                         .processInstanceBusinessKey(orderCreatedValue.getRequestId().toString())
                                                                         .setVariable("orderCreatedValue", Variables.objectValue(orderCreatedValue)
                                                                                                                    .serializationDataFormat(
                                                                                                                            Variables.SerializationDataFormats.JAVA)
                                                                                                                    .create())
                                                                         .correlateWithResult();
        log.info("" + messageCorrelationResult.getResultType());

        // TODO Manage errors
        if (orderCreatedValue.getAmount() == 0) {
            throw new RuntimeException("Amount is 0");
        }
    }

    @StreamListener(KafkaConfig.PaymentCreatedBinding.PAYMENT_CREATED_SINK)
    public void handle(@Payload PaymentCreatedValue paymentCreatedValue, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        log.info("Received: " + paymentCreatedValue + " with key: " + key);

        long correlatingInstances = processEngine.getRuntimeService().createExecutionQuery()
                                                 .messageEventSubscriptionName(paymentCreatedValue.getClass().getSimpleName()) //
                                                 .processInstanceBusinessKey(paymentCreatedValue.getRequestId().toString()) //
                                                 .count();

        if (correlatingInstances == 1) {
            log.info("Correlating " + paymentCreatedValue + " to waiting flow instance");

            MessageCorrelationResult messageCorrelationResult = processEngine.getRuntimeService()
                                                                             .createMessageCorrelation(paymentCreatedValue.getClass().getSimpleName())
                                                                             .processInstanceBusinessKey(paymentCreatedValue.getRequestId()
                                                                                                                            .toString())
                                                                             .setVariable("paymentCreatedValue", Variables
                                                                                     .objectValue(paymentCreatedValue)
                                                                                     .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
                                                                                     .create())
                                                                             .correlateWithResult();

            log.info("" + messageCorrelationResult.getResultType());
        } else {
            throw new IllegalStateException("Cannot correlate message with existing process instances");
        }
    }
}
