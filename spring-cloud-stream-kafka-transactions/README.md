# Spring Cloud Stream Kafka Transaction example
This example consist of the following components:
* REST endpoint to send commands
* Kafka topic 'command'
* A service that has the responsibilities to send Commands and receive Events
* Database tables containing commands and events
 
## Sending commands
You can send commands through the following command: 
````
curl -X POST http://localhost:8080/command -H 'Content-Type: application/json' -d '{"text":"text of the message","name" : "failtx"}'
````

### Simulating errors
You can simulate errors in the command sender or in the event receiver, in order to test transaction rollback.

To test failure in command sender, set `"name":"failtx"` as payload in the command

To test failure in event receiver, set `"name":"failrx"` as payload in the command