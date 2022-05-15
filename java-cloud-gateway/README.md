스프링 클라우드 Gateway 는 Netty 기반임
- https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-starter


### request
route
```shell
http :8080/get
```

circuitbreaker
```shell
http :8080/delay/3 Host:www.circuitbreaker.com
```