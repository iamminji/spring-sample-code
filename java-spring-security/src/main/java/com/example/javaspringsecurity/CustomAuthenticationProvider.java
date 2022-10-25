package com.example.javaspringsecurity;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

// AuthenticationProvider를 구현하여 인증 논리를 대체하는 방법이 유용할 수 있다.
// 기본 구현이 애플리케이션의 요구 사항에 완전히 맞지 않으면 맞춤형 인증 논리를 구현하기로 결정할 수도 있다.
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 인증 논리를 추가할 위치

        // Principal 인터페이스의 getName() 메서드를 Authentication에서 상속 받는다.
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        if ("john".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(
                    username, password, List.of()
            );
        }
        throw new AuthenticationCredentialsNotFoundException("Error in authentication!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Authentication 형식의 구현을 추가할 위치
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
