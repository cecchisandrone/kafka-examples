package com.voxloud.kafka.repository;

import com.voxloud.kafka.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
