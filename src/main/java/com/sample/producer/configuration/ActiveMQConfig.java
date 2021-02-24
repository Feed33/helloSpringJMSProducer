package com.sample.producer.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
// enable service JMS
@EnableJms
public class ActiveMQConfig {

	// topic definition
	public static final String SENSOR_TOPIC = "sensor-topic";

	public static final String ORDER_TOPIC = "order-topic";
	
	// ActiveMQ broker params from resources/application.properties
	@Value("${spring.activemq.broker-url}")
	String brokerUrl;
	
	@Value("${spring.activemq.user}")
	String userName;
	
	@Value("${spring.activemq.password}")
	String password;

	// ActiveMQ connection factory in order to set JMS service configuration
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(userName);
        connectionFactory.setPassword(password);
        return connectionFactory;
	}

	// Serialize message content to json using TextMessage
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
	// JMS template in order to set connection factory type and pub/sub domain
	@Bean
	public JmsTemplate jmsTemplate(){
	    JmsTemplate template = new JmsTemplate();
	    template.setConnectionFactory(connectionFactory());
	    template.setMessageConverter(jacksonJmsMessageConverter());
	    template.setPubSubDomain(true);
	    return template;	    
	}
	
	// broker configuration. Broker is mandatory in a pub/sub architecture
	@Bean
	public BrokerService broker() throws Exception {
	    final BrokerService broker = new BrokerService();
	    broker.addConnector(brokerUrl);
	    broker.addConnector("vm://localhost");
	    broker.setPersistent(false);
	    return broker;
	}

}
