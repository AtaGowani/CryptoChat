# CS 4389 | Private Messaging App | Messaging

## Description
End-to-end working solution for this component requires two servers to be running - one for the messaging API endpoint, and the other for the RabbitMQ server that handling asynchronous messaging.

## Major Components

### RabbitMQ
This is used as an asynchronous message broker to handle messages users send amongst each other. The reason for using a message broker implemented with queues because our app will not store user messages in a database as a security feature. This service offers more reliability and support for asynchronous messaging so that users do not have to be online in order to be sent messages and there is more flexibility in message coordination without sacrificing reliable message delivery.

#### Running RabbitMQ
1. cd into the server location bin. With the default installation location, this looks like: `cd "C:\Program Files\RabbitMQ Server\rabbitmq_server-3.8.9\sbin`  

2. run the following as admin: `rabbitmq-server start`  

3. to view the RabbitMQ Management Console  
    3.1. if using for the first time, install the plugin using `rabbitmq-plugins.bat enable rabbitmq_management`  
    3.2. the console is viewable at the default port :15672
    
### Java Spring Boot
* supports the messaging REST API
* In order to run successfully, three environment variables need to be set as per application.properties: RABBITMQ_USERNAME, RABBITMQ_PASSWD, and RABBITMQ_HOST

## Project Structure 
Source code components are in src/main/java inside the com.securemsgapp package. There are four subpackages within:
* controller
    * MessageController - the REST API that the frontend components will communicate with in order to send messages
* config
    * RabbitMQConfig - adds the needed configuration parameters to an already live RabbitMQ server
* model
    * Message - represents and encompasses all the relevant information about a message that wants to be sent
	* MessageList - holds the arrayLists used  to store the messages so that they can be return as an array of JSON messages
* service
    * RabbitMQListener - class for objects that listen for messages on a queue and consumes these messages as they are retrieved
    * RabbitMQSender - class for objects that send messages to a queue
* SecureMsgAppApplication - driver that starts and runs the messaging API backend
* ServletInitializer - servlet that allows deploying the backend components as a WAR file
    
## Message API Definition
The base endpoint is dynamic and will change depending on deployment. This definition just includes the relative paths.  

### /send
* Usage: Sends a message from one user and adds it to the intended recipient's message queue
* Returns: HTTP status code
* Request method: POST
* Request parameters: requestParamater "to", which holds the recipient's name, requestParameter "from", which holds the sender's name, and the requestParamater "msg", which holds the massage that one wants to send to the recipient.
* An example call using the local host is: http://localhost:8080/msg-api/send?to=Sadie&from=Ali&msg=hi

### /mailbox
* Usage: Retrieves all the messages in the calling user's message queue
* Returns: JSON array with messages a local list of messages.
* Request method: GET
* Request Parameter: requestParamater "to", which holds the recipient's name
* An example call using the local host is: http://localhost:8080/msg-api/mailbox?to=Sadie


## Development Notes
* Open secure_messaging_app/messaging as a standalone Java project. This project is written and built for Windows systems.
* Built with Maven. To download all necessary project dependencies, use `mvn clean install`
    * If this does not work and you are using IntelliJ, you can also right click the name of the project > Maven > Reload project
* RabbitMQ server must be started separately. All other components are configured to deploy as a WAR file on a local Tomcat instance on `localhost:8080`

##### Version Information
Java SDK:           1.8.0_261  
RabbitMQ Server:    3.8.9

### json-simple
This project uses an unmodified json-simple jar, which can be found at https://github.com/fangyidong/json-simple
and uses an Apache-2.0 License, which is found in the LICENSE.txt file.

## TODO
* exception handling
* blockchain applications + separation of duties policy to check code delivery
* defining, implementing, and polishing the rest of the message API, including fully integrating with message broker services
* authentication at API endpoints
* implementation of message directing - message publishing and listening pattern that ensures each user only receives their own valid messages
* key access - returning requested public keys to users
* some key verification protocols - either here or in `backend` component
* deploy solution non-locally, including any needed integrations