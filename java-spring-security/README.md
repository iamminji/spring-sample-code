## Security Bean

자동으로 구성되는 빈

#### UserDetailsService
사용자에 관한 세부 정보는 스프링 시큐리티로 `UserDetailsService` 계약을 구현하는 객체가 관리한다.

#### PasswordEncoder
`PasswordEncoder` 는 2가지 일을 한다.
- 암호를 인코딩한다.
- 암호가 기존 인코딩과 일치하는지 확인한다.

#### AuthenticationProvider
`AuthenticationProvider` 는 인증 논리를 정의하고 사용자와 암호의 관리를 위임한다.
`AuthenticationProvider` 의 기본 구현은 `UserDetailsService` 및 `PasswordEncoder` 에 제공된 기본 구현을 이용한다.

## 사용자 관리

- 스프링 시큐리티에서 사용자를 기술하는 `UserDetails`
- 사용자가 실행할 수 있는 작업을 정의하는 `GrantedAuthority`
- `UserDetailsService` 계약을 확장하는 `UserDetailsManager`, 상속된 동작 외에 사용자 만들기, 사용자의 암호 수정이나 삭제 등의 작업도 지원한다.