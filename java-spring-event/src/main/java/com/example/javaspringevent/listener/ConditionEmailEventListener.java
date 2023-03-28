package com.example.javaspringevent.listener;

import com.example.javaspringevent.event.EmailEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ConditionEmailEventListener {

    @EventListener(condition = "#event.success")
    public void handleSuccessful(EmailEvent event) {
        System.out.println("(success) email event 3 :" + event);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleTransactionalDoBeforeCommit(EmailEvent event) {
        System.out.println("email event before commit:" + event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTransactionalDoAfterCommit(EmailEvent event) {
        System.out.println("email event after commit:" + event);
    }
}
