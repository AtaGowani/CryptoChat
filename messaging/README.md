# CS 4389 | Private Messaging App | Messaging

## Description
End-to-end working solution for this component requires two servers to be running - one for the messaging API endpoint, and the other for the RabbitMQ server that handling asynchronous messaging.

## Major Components
* RabbitMQ
    * used as an asynchronous message broker to handle messages users send amongst each other.
    * reason for using a message broker implemented with queues because our app will not store user messages in a database as a security feature, and this service 
    * this service offers more reliability and support for asynchronous messaging so that users do not have to be online in order to be sent messages and there is more flexibility in message coordination
    
* Java Spring Boot
    * supports the messaging REST API


## Project Structure 
Source code components are in src/main/java inside the com.securemsgapp package. There are four subpackages within:
    * controller
        * MessageController - the REST API that the frontend components will communicate with in order to send messages
    * config
        * RabbitMQConfig - adds the needed configuration parameters to an already live RabbitMQ server
    * model
        * Message - represents and encompasses all the relevant information about a message that wants to be sent
    * service
        * RabbitMQListener - class for objects that listen for messages on a queue and consumes these messages as they are retrieved
        * RabbitMQSender - class for objects that send messages to a queue
    * SecureMsgAppApplication - driver that starts and runs the messaging API backend
    * ServletInitializer - servlet that allows deploying the backend components as a WAR file
    
## Message API Definition
The base endpoint is dynamic and will change depending on deployment. This definition just includes the relative paths.

#### /send
* Request type: POST
* Request body: the encrypted contents of the message that the user wishes to send
    
## Development Notes
* Open secure_messaging_app/messaging as a standalone Java project. This project is written and built for Windows systems.
* Built with Maven. To download all necessary project dependencies, use `mvn clean install`
    * If this does not work and you are using IntelliJ, you can also right click the name of the project > Maven > Reload project
* RabbitMQ server must be started separately. All other components are configured to deploy as a WAR file on a local Tomcat instance on `localhost:8080`

##### Version Information
Java SDK: 1.8.0_261
    

## TODO
* defining, implementing, and polishing the rest of the message API, including fully integrating with message broker services
* authentication at API endpoints
* implementation of message directing - message publishing and listening pattern that ensures each user only receives their own valid messages
* key access - returning requested public keys to users
* some key verification protocols - either here or in `backend` component
* deploy solution non-locally, including any needed integrations