package com.voxloud.kafka.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = CreateEvent.class, name = "create_event"), @JsonSubTypes.Type(value = DeleteEvent.class, name =
        "delete_event")})
public class BaseEvent {

    private Date timestamp;

    private String type;
}
