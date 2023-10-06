package com.openclassrooms.starterjwt.security.jwt;

import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtUtilsTest {
    private  JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();
        jwtUtils.setJwtSecret("test");
        jwtUtils.setJwtExpirationMs(1230000);

    }

    @Test
    void generateJwtToken() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(new UserDetailsImpl(10l,"yoga@yoga.com","firstname","lastname",false,"password"));
        assertThat(jwtUtils.generateJwtToken(authentication)).asString();
    }

    @Test
    void getUserNameFromJwtToken() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(new UserDetailsImpl(10l,"yoga@yoga.com","firstname","lastname",false,"password"));
        assertThat(jwtUtils.getUserNameFromJwtToken(jwtUtils.generateJwtToken(authentication))).isEqualTo("yoga@yoga.com");
    }

    @Test
    void validateJwtTokenWhenIsTrue() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(new UserDetailsImpl(10l,"yoga@yoga.com","firstname","lastname",false,"password"));
        assertThat(jwtUtils.validateJwtToken(jwtUtils.generateJwtToken(authentication))).isTrue();
    }

    @Test
    void validateJwtTokenWhenIsFalse() {
        String token = "cenestpasuntoken";
        assertThat(jwtUtils.validateJwtToken(token)).isFalse();
        token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5b2dhQHlvZ2EuY29tIiwiaWF0IjoxNjg4ODIxODg5LCJleHAiOjE2ODg4MzM4ODl9.JMWLr4X4c-4uWZKWoUJ7XHjnUE3p1SJa942RGPX5Rex4kJCdtycT5-tRbuthSrlSNizpff-VP0-d0a6-aQ";
        assertThat(jwtUtils.validateJwtToken(token)).isFalse();
        token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5b2dhQHlvZ2EuY29tIiwiaWF0IjoxNjg4ODI2Mzg4LCJleHAiOjE2ODg4MjYzODh9.6Gbs2_M1c71UVeN1-GJPQfCz9JPf1qWOy1bb1Ylk5LMIi583HkROF9-0DPZxnOWrh2zsAlfiGxDTq82upP1OpA";
        assertThat(jwtUtils.validateJwtToken(token)).isFalse();
        token="";
        assertThat(jwtUtils.validateJwtToken(token)).isFalse();
    }
}