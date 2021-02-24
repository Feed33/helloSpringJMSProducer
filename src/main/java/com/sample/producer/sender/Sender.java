package com.sample.producer.sender;

import static com.sample.producer.configuration.ActiveMQConfig.ORDER_TOPIC;
import static com.sample.producer.configuration.ActiveMQConfig.SENSOR_TOPIC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.sample.data.Order;
import com.sample.data.Sensor;

@Service
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessageTopic(Sensor sensor) {
    	System.out.println("sending with convertAndSend() to topic "+ SENSOR_TOPIC +" <" + sensor + ">");
        jmsTemplate.convertAndSend(SENSOR_TOPIC, sensor);
    }
    
    public void sendOrderTopic(Order order) {
    	System.out.println("sending with convertAndSend() to topic "+ ORDER_TOPIC +" <" + order + ">");
        jmsTemplate.convertAndSend(ORDER_TOPIC, order);
    }
}
