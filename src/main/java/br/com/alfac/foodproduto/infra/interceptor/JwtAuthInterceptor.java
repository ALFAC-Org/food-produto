package br.com.alfac.foodproduto.infra.interceptor;

import br.com.alfac.foodproduto.infra.helper.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private static final String AUTEHNTICATED = "AUTHENTICATED";
    private static final String ANONYMOUS = "ANONYMOUS";

    // List of routes to be ignored
    private static final List<String> IGNORED_ROUTES = Arrays.asList(
            "/api/v1/health-check"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Ignore the health-check route
        String requestURI = request.getRequestURI();

        // Ignore the routes in the list
        if (IGNORED_ROUTES.contains(requestURI)) {
            return true;
        }

        String token = request.getHeader("auth");

        if (token == null) {
            return true;
        }

        if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
            response.setStatus(401);
            return false;
        } else if (AUTEHNTICATED.equalsIgnoreCase(JwtHelper.getWho(token)) || ANONYMOUS.equalsIgnoreCase(JwtHelper.getWho(token)))
            return true;
        else {
            response.setStatus(403);
            return false;
        }

    }

}