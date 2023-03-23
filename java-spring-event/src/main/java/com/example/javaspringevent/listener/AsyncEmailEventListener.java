package com.example.javaspringevent.listener;

import com.example.javaspringevent.event.EmailEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncEmailEventListener {

    @Async
    @EventListener
    public void sendMsgEvent(EmailEvent event) {
        System.out.println("(async) email event 1 :" + event);
    }
}
