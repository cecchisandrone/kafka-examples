package com.voxloud.kafka.event;

import lombok.Data;

@Data
public class CreateEventData {
    private Long id;
    private String name;
}
