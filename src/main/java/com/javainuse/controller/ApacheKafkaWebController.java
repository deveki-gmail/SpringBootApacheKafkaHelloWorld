package com.javainuse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.javainuse.service.KafkaSender;

@RestController
@RequestMapping(value = "/javainuse-kafka/")
public class ApacheKafkaWebController {

	@Autowired
	KafkaSender kafkaSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		kafkaSender.send(message);

		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}
	
	@GetMapping(value = "/startproducer")
	public String start() {
		
		
		new Thread(new MyRunnable(kafkaSender)).start();

		return "Producer started successfully";
	}

}
class MyRunnable implements Runnable{
	
	KafkaSender kafkaSender;
	
	public MyRunnable(KafkaSender kafkaSender){
		this.kafkaSender = kafkaSender;
	}
	
	public void run(){
		Faker faker = new Faker();
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			kafkaSender.send(faker.hobbit().quote());
		}
	}
}
