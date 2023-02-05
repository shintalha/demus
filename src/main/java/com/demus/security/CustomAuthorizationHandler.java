package com.demus.security;

import com.demus.model.user.Session;
import com.demus.repository.SessionRepository;
import com.demus.security.helpers.CookieHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

import static com.demus.constants.ConstantParameter.OAUTH_COOKIE_NAME;
import static com.demus.constants.ConstantParameter.SESSION_COOKIE_NAME;

@Service
public class CustomAuthorizationHandler {
    @Autowired
    SessionRepository sessionRepository;

    @SneakyThrows
    public void accessDenied(HttpServletRequest request, HttpServletResponse response, Exception authException) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{ \"error\": \"Access Denied\" }");
    }

    @SneakyThrows
    public void successAuth(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  {
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        UUID uuidObj = UUID.randomUUID();
        String uuid = uuidObj.toString();
        sessionRepository.save(new Session(uuid, principal.getAttribute("id"), 21600L)); //21600 seconds
        response.addHeader(HttpHeaders.SET_COOKIE, CookieHelper.generateExpiredCookie(OAUTH_COOKIE_NAME));
        response.addHeader(HttpHeaders.SET_COOKIE, CookieHelper.generateCookie(SESSION_COOKIE_NAME, uuid, Duration.ofHours(6)));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{ \"status\": \"success\" }");
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        response.addHeader("Access-Control-Allow-Credentials", "true");
    }

    @SneakyThrows
    public void failureAuth(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader(HttpHeaders.SET_COOKIE, CookieHelper.generateExpiredCookie(OAUTH_COOKIE_NAME));
        response.getWriter().write("{ \"status\": \"failure\",  \"exception\": \"%s\"}".formatted(exception.toString()));
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        response.addHeader("Access-Control-Allow-Credentials", "true");
    }
}
