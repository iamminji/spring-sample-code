package com.example.rs.resourceserver;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.oauth2.core.converter.ClaimConversionService;
import org.springframework.security.oauth2.jwt.*;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.nimbusds.jwt.SignedJWT.parse;

public class SampleJwtDecoder implements JwtDecoder {

    // NimbusJWtDecoder 참고
    private Jwt createJwt(String token, JWT parsedJwt) throws ParseException {

        Map<String, Object> headers = new LinkedHashMap<>(parsedJwt.getHeader().toJSONObject());
        Map<String, Object> claims = new LinkedHashMap<>(parsedJwt.getJWTClaimsSet().toJSONObject());

        claims.put(JwtClaimNames.IAT, toInstance(claims.get(JwtClaimNames.IAT)));
        claims.put(JwtClaimNames.EXP, toInstance(claims.get(JwtClaimNames.EXP)));

        return Jwt.withTokenValue(token)
                .headers((h) -> h.putAll(headers))
                .claims((c) -> c.putAll(claims))
                .build();
    }

    private Instant toInstance(Object value) {
        return ClaimConversionService.getSharedInstance().convert(value, Instant.class);
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            SignedJWT signedJwt = parse(token);
            if (validate(signedJwt)) {
                throw new BadJwtException("Invalid token found");
            }
            // FIXME 여기에 clam validate 도 넣어 주면 좋다.
            return createJwt(token, signedJwt);
        } catch (JOSEException | ParseException e) {
            e.printStackTrace();
            throw new JwtException("Failed to parse token");
        }
    }
    private boolean validate(SignedJWT jwt) throws JOSEException {
        // JWT 를 만들 때 사용했던 값을 그대로 사용

//        JWSVerifier verifier = new MACVerifier("KEY".getBytes());
//        return jwt.verify(verifier);
        return true;
    }
}
