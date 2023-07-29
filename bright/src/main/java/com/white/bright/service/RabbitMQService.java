package com.white.bright.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@AllArgsConstructor
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Метод получаения сообщения с ожиданием.
     * @param queueName название очереди
     * @return Future результата обработки сообщение
     */
    public Future<String> receiveMessage(String queueName) {
        // Ожидание ответа с помощью CompletableFuture
        CompletableFuture<String> future = new CompletableFuture<>();

        // Установка таймаута ожидания ответа
        rabbitTemplate.setReplyTimeout(5000);

        // Установка таймаута ожидания ответа
        rabbitTemplate.setReceiveTimeout(5000);

        String response = (String) rabbitTemplate.receiveAndConvert(queueName);

        // Завершение CompletableFuture с ответом
        future.complete(response);

        return future;
    }

    public void sendMessage(String queueName, String message) {
        Message rabbitMessage = MessageBuilder.withBody(message.getBytes()).build();
        rabbitTemplate.send("", queueName, rabbitMessage);
        System.out.println("Message sent async: " + message);
    }

}
