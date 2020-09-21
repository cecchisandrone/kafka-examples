docker exec voxloud_kafka_1 kafka-topics --delete --zookeeper default:2181 --topic event
docker exec voxloud_kafka_1 kafka-topics --delete --zookeeper default:2181 --topic order
