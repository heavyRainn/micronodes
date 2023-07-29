package com.black.dark;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class DarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(DarkApplication.class, args);
	}

	@Bean
	public Queue initMessageQueue() {
		return new Queue("message_queue", true);
	}

	@Bean
	public Queue initResultQueue() {
		return new Queue("result_queue", true);
	}

}
