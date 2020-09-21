package com.voxloud.kafka.controller;

import java.util.Date;

import com.voxloud.kafka.message.json.BaseEvent;
import com.voxloud.kafka.message.json.CreateEvent;
import com.voxloud.kafka.message.json.CreateEventData;
import com.voxloud.kafka.message.json.DeleteEvent;
import com.voxloud.kafka.message.json.DeleteEventData;
import com.voxloud.kafka.producer.MessageProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonMessageController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/create-event")
    public void createEvent(@RequestParam(name = "partition", required = false) Integer partition, @RequestParam(name = "id") Long id) {

        BaseEvent event = CreateEvent.builder().type("create_event").id(id).data(CreateEventData.builder().name("Hello").build())
            .timestamp(new Date()).build();

        messageProducer.sendEvent(partition, event.getId(), event);
    }

    @PostMapping("/delete-event")
    public void deleteEvent(@RequestParam(name = "partition", required = false) Integer partition, @RequestParam(name = "id") Long id) {

        BaseEvent event = DeleteEvent.builder().type("delete_event").id(id).data(DeleteEventData.builder().result("ok").build())
            .timestamp(new Date()).build();

        messageProducer.sendEvent(partition, event.getId(), event);
    }
}