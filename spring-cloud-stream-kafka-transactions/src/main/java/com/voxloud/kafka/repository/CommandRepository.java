package com.voxloud.kafka.repository;

import com.voxloud.kafka.entity.Command;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
