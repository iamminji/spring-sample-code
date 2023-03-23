package com.example.javaspringevent.publisher;

import com.example.javaspringevent.event.EmailEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EmailPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public EmailPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEmailEvent(EmailEvent event) {
        eventPublisher.publishEvent(event);
    }

    public void publishMsgEvent(String msg) {
        eventPublisher.publishEvent(msg);
    }
}
