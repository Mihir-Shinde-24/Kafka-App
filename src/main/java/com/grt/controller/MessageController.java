package com.grt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grt.kafka.KafkaProducer;
import com.grt.payload.User;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

	@Autowired
	private KafkaProducer kafkaProducer;
	
	// http://localhost:8080/api/v1/kafka/publish?message=Hello world
	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam("message") String message)
	{
		kafkaProducer.sendStringMessage(message);
		
		return ResponseEntity.status(HttpStatus.OK).body("Message added to Topic");
	}
	
	@PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody User user)
	{
//		System.out.println(user);
		kafkaProducer.sendJsonMessage(user);
		
		return ResponseEntity.status(HttpStatus.OK).body("Json Message added to Topic");
	}
}
