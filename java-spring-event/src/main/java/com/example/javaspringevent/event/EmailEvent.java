package com.example.javaspringevent.event;

public class EmailEvent {
    private String message;

    private boolean success;

    public EmailEvent(String message) {
        this.message = message;
    }

    public EmailEvent(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "EmailEvent{" +
            "message='" + message + '\'' +
            ", success=" + success +
            '}';
    }
}
