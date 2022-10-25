package com.example.javaspringsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        // 모든 요청에 인증이 필요하다.
        http.authorizeHttpRequests()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 사용자를 메모리에 저장하기 위해 UserDetailsService 선언
        var userDetailsService =
                new InMemoryUserDetailsManager();

        // 모든 세부 정보를 지정해 사용자를 정의
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        // UserDetailsService에서 관리하도록 사용자 추가
        userDetailsService.createUser(user);

        // 이제 configure() 메서드에서 UserDetailsService와 PasswordEncoder가 설정됨
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
