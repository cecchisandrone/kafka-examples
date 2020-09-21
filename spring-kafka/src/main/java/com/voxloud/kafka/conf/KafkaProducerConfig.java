package com.voxloud.kafka.conf;

import java.util.HashMap;
import java.util.Map;

import com.voxloud.kafka.message.avro.OrderCanceled;
import com.voxloud.kafka.message.avro.OrderCreated;
import com.voxloud.kafka.message.json.BaseEvent;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.kafka.serializers.subject.RecordNameStrategy;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.schemaRegistryAddress}")
    private String schemaRegistryAddress;

    @Bean
    public ProducerFactory<Long, BaseEvent> eventProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<Long, BaseEvent> eventKafkaTemplate() {
        return new KafkaTemplate<>(eventProducerFactory());
    }

    private Map<String, Object> getAvroSerializationProps() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryAddress);
        configProps.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, false);
        configProps.put(KafkaAvroSerializerConfig.VALUE_SUBJECT_NAME_STRATEGY, RecordNameStrategy.class);
        return configProps;
    }

    @Bean
    public ProducerFactory<Long, OrderCreated> orderCreatedProducerFactory() {
        Map<String, Object> configProps = getAvroSerializationProps();
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<Long, OrderCreated> orderCreatedKafkaTemplate() {
        return new KafkaTemplate<>(orderCreatedProducerFactory());
    }

    @Bean
    public ProducerFactory<Long, OrderCanceled> orderCanceledProducerFactory() {
        Map<String, Object> configProps = getAvroSerializationProps();
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<Long, OrderCanceled> orderCanceledKafkaTemplate() {
        return new KafkaTemplate<>(orderCanceledProducerFactory());
    }
}
