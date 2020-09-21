#!/bin/bash
docker exec voxloud_kafka_1 kafka-topics --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 3 --topic event
docker exec voxloud_kafka_1 kafka-topics --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 3 --topic order
