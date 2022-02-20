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
	
	private Boolean flag = false;

	@Autowired
	KafkaSender kafkaSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		kafkaSender.send(message);

		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}
	
	@GetMapping(value = "/startproducer")
	public String start() {
		
		flag = true;
		new Thread(new MyRunnable(kafkaSender, flag)).start();

		return "Producer started successfully";
	}
	
	@GetMapping(value = "/stopproducer")
	public String stop() {
		
		flag = false;

		return "Producer stoped successfully";
	}

}
class MyRunnable implements Runnable{
	
	KafkaSender kafkaSender;
	Boolean flag;
	public MyRunnable(KafkaSender kafkaSender, Boolean flag){
		this.kafkaSender = kafkaSender;
		this.flag = flag;
	}
	
	public void run(){
		Faker faker = new Faker();
		while(flag.booleanValue()){
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
