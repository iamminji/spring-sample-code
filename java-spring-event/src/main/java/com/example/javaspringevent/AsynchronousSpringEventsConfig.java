package com.example.javaspringevent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class AsynchronousSpringEventsConfig {

    @Bean(name="applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        // listener will asynchronously deal with the event in a separate thread
        SimpleApplicationEventMulticaster eventMulticaster
            = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }

}
