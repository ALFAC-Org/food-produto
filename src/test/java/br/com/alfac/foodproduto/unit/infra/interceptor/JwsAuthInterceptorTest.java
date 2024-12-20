package br.com.alfac.foodproduto.unit.infra.interceptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.alfac.foodproduto.infra.interceptor.JwtAuthInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import br.com.alfac.foodproduto.infra.helper.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class JwsAuthInterceptorTest {
    private JwtAuthInterceptor jwtAuthInterceptor;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;

    @BeforeEach
    void setUp() {
        jwtAuthInterceptor = new JwtAuthInterceptor();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        handler = new Object();
    }

    @Test
    void testPreHandleWithNoAuthHeader() {
        when(request.getHeader("auth")).thenReturn(null);

        boolean result = jwtAuthInterceptor.preHandle(request, response, handler);

        assertTrue(result);
    }

    @Test
    void testPreHandleWithInvalidToken() {
        try (MockedStatic<JwtHelper> mockedJwtHelper = mockStatic(JwtHelper.class)) {
            when(request.getHeader("auth")).thenReturn("Bearer invalidToken");
            mockedJwtHelper.when(() -> JwtHelper.getWho("invalidToken")).thenReturn("INVALID");

            boolean result = jwtAuthInterceptor.preHandle(request, response, handler);

            assertFalse(result);
            verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Test
    void testPreHandleWithNoToken() {
        when(request.getHeader("auth")).thenReturn("");

        boolean result = jwtAuthInterceptor.preHandle(request, response, handler);

        assertFalse(result);
        verify(response).setStatus(401);
    }

    @Test
    void testPreHandleWithMalformedToken() {
        when(request.getHeader("auth")).thenReturn("InvalidTokenFormat");

        boolean result = jwtAuthInterceptor.preHandle(request, response, handler);

        assertFalse(result);
        verify(response).setStatus(401);
    }
}