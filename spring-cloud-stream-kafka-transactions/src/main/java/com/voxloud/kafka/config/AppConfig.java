package com.voxloud.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding({Source.class, Sink.class})
public class AppConfig {

}


