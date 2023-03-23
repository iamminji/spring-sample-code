package com.example.javaspringevent.listener;

import com.example.javaspringevent.event.EmailEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConditionEmailEventListener {

    @EventListener(condition = "#event.success")
    public void handleSuccessful(EmailEvent event) {
        System.out.println("(success) email event 3 :" + event);
    }

}
