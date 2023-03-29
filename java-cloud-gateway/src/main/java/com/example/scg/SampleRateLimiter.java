package com.example.scg;

import java.util.HashMap;
import java.util.Map;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import reactor.core.publisher.Mono;

public class SampleRateLimiter extends AbstractRateLimiter<SampleRateLimiter.Config> {

    public SampleRateLimiter() {
        super(SampleRateLimiter.Config.class, "sample-rate-limiter", null);
    }

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-RateLimit-Remaining", "3");
        Response response = new Response(true, headers);
//        Response response = new Response(false, new HashMap<>());
        // consumed
        return Mono.just(response);
    }

    public static class Config {
    }
}
