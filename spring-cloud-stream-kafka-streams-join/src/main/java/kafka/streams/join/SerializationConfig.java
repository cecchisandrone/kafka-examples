package kafka.streams.join;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serde;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class SerializationConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    Serde<AccountCreationRequested> accountCreationRequestedSerde() {
        return new JsonSerde<>(
                AccountCreationRequested.class, objectMapper());
    }

    @Bean
    Serde<CompanyCreated> companyCreatedSerde() {
        return new JsonSerde<>(CompanyCreated.class, objectMapper());
    }
}
