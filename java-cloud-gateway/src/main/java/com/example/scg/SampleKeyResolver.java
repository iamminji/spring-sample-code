package com.example.scg;

import io.netty.util.internal.StringUtil;
import java.util.List;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class SampleKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String apiName = exchange.getRequest().getPath().toString();

        List<String> customerIds = exchange.getRequest().getHeaders().get("X-Customer-Id");
        if (customerIds != null && !customerIds.isEmpty() && !StringUtil.isNullOrEmpty(customerIds.get(0))) {
            return Mono.just(customerIds.get(0) + StringUtil.COMMA + apiName);
        }

        return Mono.just(StringUtil.EMPTY_STRING);
    }
}
