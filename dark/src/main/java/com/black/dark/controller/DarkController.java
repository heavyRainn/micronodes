package com.black.dark.controller;

import com.black.dark.entity.Message;
import com.black.dark.repository.EncryptedMessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/dark")
@Slf4j
@AllArgsConstructor
public class DarkController {

    private EncryptedMessageRepository encryptedMessageRepository;

    @GetMapping("/health")
    public String health() {
        return "Alive Dark";
    }

    public Iterable<Message> getAll() {
        return encryptedMessageRepository.findAll();
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id) {
        Optional<Message> optionalEntity = encryptedMessageRepository.findById(id);

        if (optionalEntity.isPresent()) {
            Message message = optionalEntity.get();
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
