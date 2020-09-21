package kafka.streams.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageChannel onboardingOutput;

    @Autowired
    private MessageChannel companyOutput;

    @PostMapping("/onboarding")
    public void sendMessage1(@RequestBody AccountCreationRequested accountCreationRequested) {
        onboardingOutput.send(MessageBuilder.withPayload(accountCreationRequested).setHeader(KafkaHeaders.MESSAGE_KEY,
                accountCreationRequested.getRequestId()).build());
    }

    @PostMapping("/company")
    public void sendMessage2(@RequestBody CompanyCreated companyCreated) {
        companyOutput.send(MessageBuilder.withPayload(companyCreated).setHeader(KafkaHeaders.MESSAGE_KEY,
                companyCreated.getRequestId()).build());
    }
}
