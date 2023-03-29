스프링 클라우드 Gateway 는 Netty 기반임
- https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-starter


### request
http://httpbin.org/

```shell
http :8080/get
```

```shell
http :8080/anything X-Customer-Id:helloWorld
```

circuitbreaker
```shell
http :8080/delay/1 Host:www.circuitbreaker.com
```