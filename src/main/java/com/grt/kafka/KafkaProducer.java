package com.grt.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.grt.payload.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaStringTemplate;
	
	@Autowired 
	private KafkaTemplate<String, User> kafkaJsonTemplate;
	
	public void sendStringMessage(String message)
	{
		log.info(String.format("Message sent -> %s", message));
		kafkaStringTemplate.send("myTopic", message);
	}
	
	public void sendJsonMessage(User user)
	{
		log.info(String.format("Json Message sent -> %s", user.toString()));
		
		Message<User> message = MessageBuilder
											.withPayload(user)
											.setHeader(KafkaHeaders.TOPIC, "myJsonTopic")
											.build();
		
		kafkaJsonTemplate.send(message);
											
	}
	
	
	
	
	
}
