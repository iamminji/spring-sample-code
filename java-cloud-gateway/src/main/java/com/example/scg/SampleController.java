package com.example.scg;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SampleController {

    @GetMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }
}
