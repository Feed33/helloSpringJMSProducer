# helloSpringJMSProducer
Spring JMS ActiveMQ embedded Producer

## How it works
1. From shell digit

```
mvn clean package && mvn spring-boot:run
```

2. In order to send a message, use postman or curl:

```
http://localhost:8081/api/send/sensor/test
```

3. Producer will send sensor data to all active [consumer](https://github.com/wiulma/helloSpringJMSConsumer)
