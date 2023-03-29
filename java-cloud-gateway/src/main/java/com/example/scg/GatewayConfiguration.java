package com.example.scg;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    private String uri = "http://httpbin.org:80";

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(p -> p
                .path("/get")
                .filters(f -> f.addRequestHeader("Hello", "World"))
                .uri(uri))
            .route(p -> p
                .host("*.circuitbreaker.com")
                .filters(f -> f.circuitBreaker(config -> config
                    .setName("mycmd")
                    .setFallbackUri("forward:/fallback")))
                .uri(uri))
            .route("ratelimiter_route", p -> p
                .path("/anything")
                .filters(f -> f.requestRateLimiter(r ->
                    r.setRateLimiter(sampleRateLimiter())
                        .setKeyResolver(sampleKeyResolver())))
                .uri(uri))
            .build();
    }

    @Bean
    public SampleRateLimiter sampleRateLimiter() {
        return new SampleRateLimiter();
    }

    @Bean
    public SampleKeyResolver sampleKeyResolver() {
        return new SampleKeyResolver();
    }
}
