package com.voxloud.kafka.event;

import lombok.Data;

@Data
public class CreateEvent extends BaseEvent {

    private CreateEventData data;
}
