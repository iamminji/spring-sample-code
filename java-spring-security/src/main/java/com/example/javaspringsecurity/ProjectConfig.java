package com.example.javaspringsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    // 아래 코드를 추가 하면 콘솔에 더 이상 auto-generated 암호가 등장 하지 않는다.
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService =
                new InMemoryUserDetailsManager();

        var user = User.withUsername("john")
                .password("1234")
                .authorities("read")
                .build();

        // UserDetailsService에서 관리하도록 user추가
        userDetailsService.createUser(user);
        return userDetailsService;
    }

    // UserDetailsService를 재정의했기 떄문에 PasswordEncoder 빈도 컨텍스트에 추가해주어야 한다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        // 모든 요청에 인증이 필요하다.
        http.authorizeHttpRequests()
                .anyRequest().authenticated();
    }
}
