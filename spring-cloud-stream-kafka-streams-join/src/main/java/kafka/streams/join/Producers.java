/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kafka.streams.join;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KeyValue;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producers {

    public static void main(String... args) {

        ObjectMapper mapper = new ObjectMapper();
        Serde<AccountCreationRequested> accountCreationRequestedSerde = new JsonSerde<>(AccountCreationRequested.class,
                mapper);
        Serde<CompanyCreated> companyCreatedSerde = new JsonSerde<>(CompanyCreated.class, mapper);

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "default:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, accountCreationRequestedSerde.serializer().getClass());

        List<KeyValue<String, AccountCreationRequested>> accountCreationRequests = Arrays.asList(
                new KeyValue<>("aaa", new AccountCreationRequested("aaa", "Pinco", "Pallo", "pincopallo@gmail.com")),
                new KeyValue<>("bbb", new AccountCreationRequested("bbb", "Bla", "Bla", "blabla@gmail.com")),
                new KeyValue<>("ccc", new AccountCreationRequested("ccc", "Gigi", "Dag", "gigidag@gmail.com")));

        DefaultKafkaProducerFactory<String, AccountCreationRequested> pf = new DefaultKafkaProducerFactory<>(props);
        KafkaTemplate<String, AccountCreationRequested> template = new KafkaTemplate<>(pf, true);
        template.setDefaultTopic("onboarding");

        for (KeyValue<String, AccountCreationRequested> keyValue : accountCreationRequests) {
            template.sendDefault(keyValue.key, keyValue.value);
        }

        List<KeyValue<String, CompanyCreated>> companies = Arrays.asList(
                new KeyValue<>("aaa", new CompanyCreated("aaa", 1L, "Pippo spa")),
                new KeyValue<>("bbb", new CompanyCreated("bbb", 2L, "Cacco spa")),
                new KeyValue<>("ccc", new CompanyCreated("ccc", 1L, "Mela spa")));

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, companyCreatedSerde.serializer().getClass());

        DefaultKafkaProducerFactory<String, CompanyCreated> pf1 = new DefaultKafkaProducerFactory<>(props);
        KafkaTemplate<String, CompanyCreated> template1 = new KafkaTemplate<>(pf1, true);
        template1.setDefaultTopic("company");

        for (KeyValue<String, CompanyCreated> keyValue : companies) {
            template1.sendDefault(keyValue.key, keyValue.value);
        }
    }
}
