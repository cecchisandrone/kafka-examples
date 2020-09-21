package com.voxloud.kafka.service;

import com.voxloud.kafka.entity.Command;
import com.voxloud.kafka.entity.Event;
import com.voxloud.kafka.repository.CommandRepository;
import com.voxloud.kafka.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class CommandEventService {

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MessageChannel output;

    public void persistCommandAndSendEvent(Command command) {

        commandRepository.save(command);
        Event event = Event.builder().name(command.getName()).text(command.getText()).commandId(command.getId()).build();

        output.send(MessageBuilder.withPayload(event).build());

        if (command.getName().equals("failtx")) {
            throw new RuntimeException("I'm failing");
        }
    }

    @StreamListener(Sink.INPUT)
    public void receiveAndPersistEvent(Event event) {
        log.info("{}", event);
        eventRepository.save(event);
        if (event.getName().equals("failrx")) {
            throw new RuntimeException("I'm failing");
        }
    }
}
