package com.example.restdemo.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.restdemo.demo.dto.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {
    private Message message;

    private List<Message> messages = new ArrayList<>(Arrays.asList(
            new Message(1, "Первый заголовок", "1-ое сообщение", LocalDateTime.of(2011, 2,3, 12, 45)),
            new Message(2, "Второй заголовок", "2-ое сообщение", LocalDateTime.of(2012, 6,30, 1, 12)),
            new Message(3, "Третий заголовок", "3-е сообщение", LocalDateTime.of(2023, 1,4, 2, 14)),
            new Message(4, "Четвертый заголовок", "4-ое сообщение", LocalDateTime.of(2024, 12,15, 12, 15))
    ));

    @PostMapping("/messages")
    public Message addMessage(@RequestBody Message message) {
        messages.add(message);
        return message;
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        int index = - 1;
        for (Message m : messages) {
            if (m.getId() == id) {
                index = messages.indexOf(m);
                messages.set(index, message);
            }
        }
        return index == -1
                ? new ResponseEntity<>(addMessage(message), HttpStatus.CREATED)
                : new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/messages/{id}")
    public void deleteMassage(@PathVariable int id) {
        messages.removeIf(p -> p.getId() == id);
    }

    @PostMapping("/message")
    public ResponseEntity<Message> setMessage(@RequestBody Message message) {
        messages.add(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/messages")
    public Iterable<Message> getMessages() {
        return messages;
    }

    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return messages.stream().filter(p -> p.getId() == id).findFirst();
    }

}
