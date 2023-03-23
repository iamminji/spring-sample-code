package com.example.javaspringevent.controller;

import com.example.javaspringevent.event.EmailEvent;
import com.example.javaspringevent.publisher.EmailPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmailPublisher emailPublisher;

    public EmployeeController(EmailPublisher emailPublisher) {
        this.emailPublisher = emailPublisher;
    }

    @GetMapping("/notify/event")
    public void publishEvent() {
        emailPublisher.publishEmailEvent(new EmailEvent("hello world"));
        emailPublisher.publishEmailEvent(new EmailEvent("hello world success false", false));
        emailPublisher.publishEmailEvent(new EmailEvent("hello world true", true));
    }
}
