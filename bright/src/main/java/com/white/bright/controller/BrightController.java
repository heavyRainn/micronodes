package com.white.bright.controller;

import com.white.bright.service.RabbitMQService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bright")
public class BrightController {

    private RabbitMQService rabbitMQService;

    @GetMapping("/health")
    public String health() {
        return "Alive Bright";
    }

    @GetMapping("/message/{message}")
    public String sendMessage(@PathVariable String message) {
        System.out.println("Http Message received: " + message);

        // отсылка сообщения без ожидания результата
        rabbitMQService.sendMessage("message_queue", message);
        System.out.println("Message sent to RabbitMq: " + message);

        // ожидаение получения результата обработки сообщения другим микросервисом и возврат результата
        String result = getResult();
        return "Got : " + result;
    }

    /**
     * Получение результата Future инкапсулированное в методе
     * @return результат выполнения Future
     */
    private String getResult() {
        String result;

        try {
            result = rabbitMQService.receiveMessage("result_queue").get();
            System.out.println("Result of future: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error of waiting of result: " + e);
            return e.toString();
        }

        return result;
    }

}
