package com.grt.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.grt.payload.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumer {
	
	
	@KafkaListener(topics = "myTopic" , groupId = "myGroup")
	public void consume(String message)
	{
		log.info(String.format("Message received -> %s", message));
	}
	
	@KafkaListener(topics = "myJsonTopic", groupId = "myGroup")
	public void consumeJson(User user)
	{
		log.info(String.format("Json Message received -> %s", user.toString()));
	}
	

}
