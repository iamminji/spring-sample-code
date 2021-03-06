# 스프링 웹플럭스
스프링 MVC 같은 전형적인 `Servlet` 기반의 웹프레임워크는 스레드 blocking과 다중 스레드로 수행된다.

즉, 요청이 들어오면 스레드 풀에서 worker thread 를 가져와서 해당 요청을 처리하며, worker thread 가 종료될 때 까지 요청 스레드는 블로킹된다.

이 떄문에 블로킹 웹 프레임워크는 요청량의 증가에 따른 확장이 어렵다.

이에 반해서 비동기 웹 프레임워크는 더 적은 수의 스레드로 높은 확장성을 성취할 수 있는데, 바로 event loop 덕분이다.

### 이벤트 루프
이벤트 루프는 클라이언트의 요청을 처리하는 핸들러가 이벤트 루프에게 이벤트를 푸시하면,
이벤트 루프는 콜백을 등록하게 된다.

비용이 드는 작업이 필요하면 이벤트 루프는 해당 작업의 콜백을 등록하고 새로운 요청은 다른 이벤트에서 처리할 수 있게 된다.

### 스프링 웹플럭스
스프링 리액티브 스택의 기본 서버 프레임워크는 Netty이다.

사용하는 어노테이션이 스프링 MVC 와 유사해서 전환이 쉽다.

## Best Practice
- Don't mix blocking and non-blocking APIs
- Vertical non-blocking slices
- Don't put non-blocking code behind synchronous APIs
- Compose single, deferred, request handling chain
- Don't use `block()`, `subscribe()`, and the like
- Let Spring MVC handle it