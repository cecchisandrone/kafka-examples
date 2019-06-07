package com.example.avro;

import org.apache.avro.Schema;
import org.springframework.cloud.stream.schema.avro.SubjectNamingStrategy;

public class CustomSubjectNamingStrategy implements SubjectNamingStrategy {
    @Override
    public String toSubject(Schema schema) {
        return schema.getFullName().toLowerCase();
    }
}
