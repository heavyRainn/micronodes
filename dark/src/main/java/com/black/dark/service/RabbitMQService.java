package com.black.dark.service;

import com.black.dark.entity.Message;
import com.black.dark.repository.EncryptedMessageRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;
    private final EncryptedMessageRepository encryptedMessageRepository;

    @RabbitListener(queues = "message_queue")
    public void receive(String message) {
        System.out.println("Received message from RabbitMq: " + message);

        Message saved = encryptedMessageRepository.save(new Message(message));
        System.out.println("Saved message to Postgres: " + saved);

        rabbitTemplate.convertAndSend("result_queue", message);
        System.out.println("Sent message to RabbitMq: " + message);
    }

}

