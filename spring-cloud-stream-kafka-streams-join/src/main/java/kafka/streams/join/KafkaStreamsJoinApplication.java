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

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class KafkaStreamsJoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamsJoinApplication.class, args);
    }

    @EnableBinding({KStreamProcessorX.class, Topics.class})
    public static class KStreamJoinProcessor {

        @Autowired
        private Serde<AccountCreationRequested> accountCreationRequestedSerde;

        @Autowired
        private Serde<CompanyCreated> companyCreatedSerde;

        @StreamListener
        public void process(
                @Input("onboarding") KStream<String, AccountCreationRequested> accountCreationRequestedStream,
                @Input("company") KStream<String, CompanyCreated> companyCreatedStream) {

            accountCreationRequestedStream.join(companyCreatedStream,
                    (value1, value2) -> {
                        System.out.println("Joining " + value1.getEmail() + " with " + value2.getName());
                        return null;
                    }, JoinWindows.of(15000), Joined.with(Serdes.String(), accountCreationRequestedSerde, companyCreatedSerde));
        }
    }

    interface KStreamProcessorX {
        @Input("onboarding")
        KStream<?, ?> onboarding();

        @Input("company")
        KStream<?, ?> company();
    }

    interface Topics {

        @Output("onboardingOutput")
        MessageChannel onboardingOutput();

        @Output("companyOutput")
        MessageChannel companyOutput();
    }
}
