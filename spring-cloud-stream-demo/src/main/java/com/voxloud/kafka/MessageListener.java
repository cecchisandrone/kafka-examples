package com.voxloud.kafka;

import com.voxloud.kafka.user.UserValue;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableBinding({Sink.class, Source.class})
public class MessageListener {

    @StreamListener(Sink.INPUT)
    public void handle(@Payload UserValue user, @Headers Map<String, Object> headers,
                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        System.out.println("Received: " + user + " with key: " + key + " and headers: " + headers);
        if (user.getName().equals("error")) {
            throw new RuntimeException("error occurred");
        }
    }

    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("Handling ERROR: " + message);
    }
}
