package com.voxloud.kafka.event;

import lombok.Data;

@Data
public class DeleteEvent extends BaseEvent {
    private DeleteEventData data;
}
