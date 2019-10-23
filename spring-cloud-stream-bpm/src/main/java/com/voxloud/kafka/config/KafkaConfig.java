package com.voxloud.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@EnableBinding({KafkaConfig.OrderCreatedBinding.class, KafkaConfig.PaymentCreatedBinding.class, KafkaConfig.ShipmentCreatedBinding.class})
public class KafkaConfig {

    // This is used to retry failed processing in @StreamListener
    @StreamRetryTemplate
    public RetryTemplate kafkaRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(1000L);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(5);
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
        return retryTemplate;
    }

    public interface OrderCreatedBinding {

        @Output("orderCreatedSource")
        MessageChannel orderCreatedSource();

        String ORDER_CREATED_SINK = "orderCreatedSink";

        @Input(ORDER_CREATED_SINK)
        SubscribableChannel orderCreatedSink();
    }

    public interface PaymentCreatedBinding {

        @Output("paymentCreatedSource")
        MessageChannel paymentCreatedSource();

        String PAYMENT_CREATED_SINK = "paymentCreatedSink";

        @Input(PAYMENT_CREATED_SINK)
        SubscribableChannel paymentCreatedSink();
    }

    public interface ShipmentCreatedBinding {
        @Output("shipmentCreatedSource")
        MessageChannel shipmentCreatedSource();
    }
}
