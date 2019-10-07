package com.voxloud.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding({KafkaConfig.OrderCreatedBinding.class, KafkaConfig.PaymentCreatedBinding.class, KafkaConfig.ShipmentCreatedBinding.class})
public class KafkaConfig {

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
