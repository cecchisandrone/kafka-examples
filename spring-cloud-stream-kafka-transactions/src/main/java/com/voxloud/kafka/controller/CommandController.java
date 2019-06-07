package com.voxloud.kafka.controller;

import com.voxloud.kafka.entity.Command;
import com.voxloud.kafka.service.CommandEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

    @Autowired
    private CommandEventService commandEventService;

    @PostMapping("/command")
    public void sendMessage(@RequestBody Command command) {
        commandEventService.persistCommandAndSendEvent(command);
    }
}
