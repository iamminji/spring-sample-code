## Resource Server

### 설명
리소스 서버는 토큰을 헤더로 받아 권한을 확인한 후 요청에 대한 응답을 줘야 한다.
본 예제에서는 이미 유효한 토큰을 인증 서버로 부터 발급을 받았다고 가정한다.

### 특징
- 토큰의 타입은 JWT 이다.
- 리소스 권한은 read 만 허가한다.

### JWT
https://jwt.io/
```shell
$ export MOCK_TOKEN=<jwt.io 로 만들어주세요>
```
#### Header
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```
#### Payload
```json
{
  "sub": "sample",
  "name": "Hello World",
  "iat": 1516239022,
  "exp": 1655639966,
  "scope": "read"
}
```
### 요청 예시
```shell
$ http :9090/api/message "Authorization: Bearer $MOCK_TOKEN"
```