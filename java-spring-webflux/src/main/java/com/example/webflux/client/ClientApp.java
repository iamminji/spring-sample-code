package com.example.webflux.client;

import com.example.webflux.entities.Hobby;
import com.example.webflux.entities.Person;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientApp {

    private static WebClient webClient = WebClient.create("http://localhost:8080?delay=2");

    public static void main(String[] args) {
        Instant start = Instant.now();
        step4();
        logTime(start);
    }

    private static void step1() {
        // block 했기 때문에 6초 걸림.
        for (int i = 1; i <= 3; i++) {
            webClient.get().uri("/person/{id}", i)
                    .retrieve()
                    .bodyToMono(Person.class)
//                    .subscribe();
                    .block();
        }
    }

    private static void step2() {
        // concurrency model 전체 2초 정도 걸림
        // non-blocking, event-loop 라서 실행 되는 쓰레드 개수 많지 않음 (thread depends on machine)
        List<Mono<Person>> personMono = Stream.of(1, 2, 3)
                .map(i -> webClient.get().uri("/person/{id}", i).retrieve().bodyToMono(Person.class))
                .collect(Collectors.toList());

        Mono.when(personMono).block();
    }

    private static void step3() {
        // nested async call
        // 6개의 요청이 있기 때문에 blocking 모델에서는 12초 (6 * 2) 가 걸리지만 non-blocking 에서는 그것보다 적게 걸린다.
        // 아래 코드는 전체 4초 정도 걸림 (첫번째 요청 2초 + 두번째 요청 2초)
        Flux.range(1, 3)
                // subscribe to mono
                .flatMap(i -> webClient.get().uri("/person/{id}", i)
                        .retrieve()
                        .bodyToMono(Person.class)
                        .flatMap(person -> webClient.get().uri("/person/{id}/hobby", i)
                                .retrieve()
                                .bodyToMono(Hobby.class)))
                // wait for all of them until the end
                .blockLast();
    }

    private static void step4() {
        // stream 방식으로 받음
        webClient.get().uri("/person/events")
                .retrieve()
                .bodyToFlux(Person.class)
                .take(4)
                .blockLast();
    }

    private static void logTime(Instant start) {
        System.out.format("\nElapsed time: %d ms\n", Duration.between(start, Instant.now()).toMillis());
    }

}
