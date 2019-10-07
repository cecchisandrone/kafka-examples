package com.voxloud.kafka;

import com.voxloud.kafka.user.UserValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageChannel output;

    @PostMapping("/send")
    public void sendMessage() {
        UserValue user = UserValue.newBuilder().setName("pinco").setSurname("pallo").setAge(19).setEmail("alessandro@gmail.com").build();
        output.send(MessageBuilder.withPayload(user).setHeader(KafkaHeaders.MESSAGE_KEY, "1").build());
        System.out.println("message sent");
    }
}
