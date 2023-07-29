package com.white.bright;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BrightApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrightApplication.class, args);
	}

	@Bean
	public Queue initMessageQueue() { return new Queue("message_queue", true); }

	@Bean
	public Queue initResultQueue() {
		return new Queue("result_queue", true);
	}
}
