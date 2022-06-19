package com.example.rs.resourceserver;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenError;

// 만들었지만 지금 예제에서는 사용하고 있지 않음
// Validator 를 추가하고 싶으면 아래 처럼 사용한다.
// 비슷하게 JwtClaimValidator 를 사용할 수도 있다.
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private static final BearerTokenError MISSING_AUDIENCE =
            new BearerTokenError("invalid_token", HttpStatus.UNAUTHORIZED,
                    "The audience required is missing", null);


    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (token.getAudience().contains("messaging")) {
            return OAuth2TokenValidatorResult.success();
        }
        return OAuth2TokenValidatorResult.failure(MISSING_AUDIENCE);
    }
}
