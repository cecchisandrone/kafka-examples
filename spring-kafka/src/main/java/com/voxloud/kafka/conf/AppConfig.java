package com.voxloud.kafka.conf;

import com.voxloud.kafka.consumer.MessageConsumer;
import com.voxloud.kafka.producer.MessageProducer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    @Bean
    public MessageConsumer messageConsumer() {
        return new MessageConsumer();
    }
}
