package com.voxloud.kafka;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voxloud.kafka.message.json.BaseEvent;
import org.junit.Before;
import org.junit.Test;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class EventSerializationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    private File createEvent;

    @Before
    public void setUp() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        createEvent = resolver.getResource("classpath:com/voxloud/kafka/create-event.json").getFile();
    }

    @Test
    public void testDeserialization() throws IOException {

        BaseEvent event = objectMapper.readValue(createEvent, BaseEvent.class);
        System.out.println(event);
    }
}