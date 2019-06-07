package com.voxloud.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voxloud.kafka.event.BaseEvent;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;

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