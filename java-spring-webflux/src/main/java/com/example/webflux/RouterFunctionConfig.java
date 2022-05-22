package com.example.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

import static org.springframework.web.reactive.function.BodyInserters.*;


@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<?> helloRouterFunction() {
        return route(GET("/hello"),
                request -> ok().body(just("Hello World!"), String.class))
                .andRoute(GET("/bye"),
                        request -> ok().body(just("See ya!"), String.class));
    }

    @Bean
    public RouterFunction<?> routes() {
        return route()
                .GET("/person/events", request -> {
                    Flux<ServerSentEvent<Map<String, Object>>> stream =
                            Flux.interval(Duration.ofSeconds(2))
                                    .map(i -> PERSON_DATA.get((i % 10 + 1)))
                                    .map(data -> ServerSentEvent.builder(data).build());
                    return ServerResponse.ok().bodyValue(fromServerSentEvents(stream));
                })
                .GET("/person/{id}", request -> {
                    Long id = Long.parseLong(request.pathVariable("id"));
                    Map<String, Object> body = PERSON_DATA.get(id);
                    return body != null ? ServerResponse.ok().bodyValue(body) : NOT_FOUND;
                })
                .GET("/person/{id}/hobby", request -> {
                    Long id = Long.parseLong(request.pathVariable("id"));
                    Map<String, Object> body = HOBBY_DATA.get(id);
                    return body != null ? ServerResponse.ok().bodyValue(body) : NOT_FOUND;
                })
                .filter((request, next) -> {
                    Duration delay = request.queryParam("delay")
                            .map(s -> Duration.ofSeconds(Long.parseLong(s)))
                            .orElse(Duration.ZERO);
                    return delay.isZero() ? next.handle(request) :
                            Mono.delay(delay).flatMap(x -> next.handle(request));
                })
                .build();
    }

    private static final Mono<ServerResponse> NOT_FOUND = ServerResponse.notFound().build();

    private static final Map<Long, Map<String, Object>> PERSON_DATA;

    static {
        PERSON_DATA = new HashMap<>();
        addPerson(1L, "Amanda");
        addPerson(2L, "Brittany");
        addPerson(3L, "Christopher");
        addPerson(4L, "Elizabeth");
        addPerson(5L, "Hannah");
        addPerson(6L, "Joshua");
        addPerson(7L, "Kayla");
        addPerson(8L, "Lauren");
        addPerson(9L, "Matthew");
        addPerson(10L, "Megan");
    }

    private static void addPerson(Long id, String name) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", id);
        map.put("name", name);
        PERSON_DATA.put(id, map);
    }


    private static final Map<Long, Map<String, Object>> HOBBY_DATA;

    static {
        HOBBY_DATA = new HashMap<>();
        addHobby(1L, "Travel");
        addHobby(2L, "Coffee Roasting");
        addHobby(3L, "Puzzles");
        addHobby(4L, "3D Printing");
        addHobby(5L, "Skiing");
        addHobby(6L, "Yoga");
        addHobby(7L, "Scuba Diving");
        addHobby(8L, "Shopping");
        addHobby(9L, "Tai Chi");
        addHobby(10L, "Kombucha brewing");
    }

    private static void addHobby(Long personId, String hobby) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("personId", personId);
        map.put("hobby", hobby);
        HOBBY_DATA.put(personId, map);
    }
}
