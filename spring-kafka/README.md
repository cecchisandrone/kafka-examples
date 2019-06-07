# Spring Kafka

This is a simple Spring Boot app to demonstrate sending and receiving of messages in Kafka using spring-kafka.

As Kafka topics are not created automatically by default, this application requires that you create the following topics manually by running: `create-topics.sh`

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

#### Message received from the 'command' topic by the basic listeners with groups foo and bar
>Received Message in group 'foo': Hello, World!<br>
Received Message in group 'bar': Hello, World!

#### Message received from the 'command' topic, with the partition info
>Received Message: Hello, World! from partition: 0

#### Message received from the 'partitioned' topic, only from specific partitions
>Received Message: Hello To Partioned Topic! from partition: 0<br>
Received Message: Hello To Partioned Topic! from partition: 3

#### Message received from the 'filtered' topic after filtering
>Received Message in filtered listener: Hello command!

#### Message (Serialized Java Object) received from the 'event' topic
>Received event command: Greetings, World!!
