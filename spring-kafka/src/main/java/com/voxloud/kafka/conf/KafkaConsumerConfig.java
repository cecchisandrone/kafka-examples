package com.voxloud.kafka.conf;

import java.util.HashMap;
import java.util.Map;

import com.voxloud.kafka.message.json.BaseEvent;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer2;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.schemaRegistryAddress}")
    private String schemaRegistryAddress;

    public ConsumerFactory<Long, BaseEvent> baseEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "spring-kafka-1");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer2.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ErrorHandlingDeserializer2.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.voxloud.kafka.message.json.BaseEvent");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.voxloud");
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 10000);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, BaseEvent> eventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, BaseEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(baseEventConsumerFactory());
        factory.setErrorHandler(new ErrorHandlerDummyImpl());
        factory.setRecordFilterStrategy(record -> record.value().getType().equals("user_create"));
        return factory;
    }

    private Map<String, Object> getAvroDeserializationProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "spring-kafka-2");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer2.class);
        props.put(ErrorHandlingDeserializer2.VALUE_DESERIALIZER_CLASS, KafkaAvroDeserializer.class.getName());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryAddress);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        return props;
    }

    public ConsumerFactory<?, ?> orderConsumerFactory() {
        Map<String, Object> props = getAvroDeserializationProperties();
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> orderKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(orderConsumerFactory());
        factory.setErrorHandler(new ErrorHandlerDummyImpl());
        return factory;
    }

    public static class ErrorHandlerDummyImpl implements ErrorHandler {

        @Override
        public void handle(Exception e, ConsumerRecord<?, ?> consumerRecord) {
            String message = consumerRecord != null ? "Error consuming message " + consumerRecord.toString() : "Error consuming message";
            log.error(message, e);
        }
    }
}
