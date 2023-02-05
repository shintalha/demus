package com.demus.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class CustomAuthorizationRedirectFilter extends OAuth2AuthorizationRequestRedirectFilter {

    @SneakyThrows
    public CustomAuthorizationRedirectFilter(
            OAuth2AuthorizationRequestResolver authorizationRequestResolver,
            AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository
    ) {
        super(authorizationRequestResolver);
        super.setAuthorizationRequestRepository(authorizationRequestRepository);
        // Reflection hack to overwrite the parent's redirect strategy
        RedirectStrategy customStrategy = (HttpServletRequest request, HttpServletResponse response, String url) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{ \"redirectUrl\": \"%s\" }".formatted(url));
            response.addHeader("Access-Control-Allow-Origin", "http://localhost:8000");
            response.addHeader("Access-Control-Allow-Credentials", "true");
        };
        Field field = OAuth2AuthorizationRequestRedirectFilter.class.getDeclaredField("authorizationRedirectStrategy");
        field.setAccessible(true);
        field.set(this, customStrategy);
    }

}
