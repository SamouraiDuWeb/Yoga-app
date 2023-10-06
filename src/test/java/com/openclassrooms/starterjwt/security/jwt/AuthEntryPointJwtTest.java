package com.openclassrooms.starterjwt.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AuthEntryPointJwtTest {
    private AuthEntryPointJwt authEntryPointJwt = new AuthEntryPointJwt();

    @Test
    void commence() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException authException = mock(AuthenticationException.class);
        authEntryPointJwt.commence(request,response,authException);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }
}