kafka-topics.bat --create --zookeeper default:2181 --replication-factor 1 --partitions 1 --topic message
kafka-topics.bat --create --zookeeper default:2181 --replication-factor 1 --partitions 5 --topic partitioned
kafka-topics.bat --create --zookeeper default:2181 --replication-factor 1 --partitions 1 --topic filtered
kafka-topics.bat --create --zookeeper default:2181 --replication-factor 1 --partitions 1 --topic event
kafka-topics.bat --create --zookeeper default:2181 --replication-factor 1 --partitions 1 --topic order
