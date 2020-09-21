# Spring Kafka

This is a simple Spring Boot app to demonstrate sending and receiving of messages in Kafka using spring-kafka.

As Kafka topics are not created automatically by default, this application requires that you create the following topics manually by running: `create-topics.sh` 
**Important:** development environment should be already running with Docker using https://bitbucket.org/voxloud/kafka

When the application runs successfully, following output is logged on to console (along with spring logs):

## Avro serialization/deserialization and Confluent schema-registry
In order to use Avro serdes, the object schemas must be present in the src/main/avro folder in order to generate source classes and schema registry must be running.
Useful Maven goals:
````
mvn avro:schema # To generate Java sources starting from the schema
mvn schema-registry:register # To register local schema to schema registry
mvn schema-registry:download # To download remote schema to local folder
````

**Important:** before running Avro examples, schemas must be registered to the referenced schema registry
