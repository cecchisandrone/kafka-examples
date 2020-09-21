package com.voxloud.kafka.message.json;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = CreateEvent.class, name = "create_event"), @JsonSubTypes.Type(value = DeleteEvent.class, name =
    "delete_event")})
public class BaseEvent {

    protected Long id;

    protected Date timestamp;

    protected String type;
}
