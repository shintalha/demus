package com.demus.config;

import com.demus.model.user.ClientToken;
import com.demus.repository.ClientTokenRepository;
import com.demus.repository.CustomStatelessAuthorizationRequestRepository;
import com.demus.security.CookieHelper;
import com.demus.service.CustomAuthorizedClientService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;

@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfig {

    @Autowired
    CustomAuthorizedClientService customAuthorizedClientService;
    @Autowired
    CustomStatelessAuthorizationRequestRepository customStatelessAuthorizationRequestRepository;
    @Autowired
    OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;
    @Autowired
    CustomAuthorizationRequestResolver customAuthorizationRequestResolver;
    @Autowired
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    @Autowired
    CustomAuthorizationRedirectFilter customAuthorizationRedirectFilter;

    public static final String OAUTH_COOKIE_NAME = "OAUTH";
    public static final String SESSION_COOKIE_NAME = "SESSION";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> {
                    authorize.anyRequest().permitAll();
                })
                .sessionManagement(config -> {
                    config.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .oauth2Login(config -> {
                    config.authorizationEndpoint(subconfig -> {
                        subconfig.authorizationRequestRepository(this.customStatelessAuthorizationRequestRepository);
                        subconfig.authorizationRequestResolver(this.customAuthorizationRequestResolver);
                    });
                    config.authorizedClientService(this.customAuthorizedClientService);
                    config.successHandler(((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
                        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
                        ClientToken clientToken = customAuthorizedClientService.getClientToken(principal.getAttribute("id"));
                        response.addHeader(HttpHeaders.SET_COOKIE, CookieHelper.generateExpiredCookie(OAUTH_COOKIE_NAME));
                        response.addHeader(HttpHeaders.SET_COOKIE, CookieHelper.generateCookie(SESSION_COOKIE_NAME, principal.getAttribute("id"), Duration.ofHours(1)));
                        response.addHeader(HttpHeaders.SET_COOKIE, CookieHelper.generateCookie("Bearer", clientToken.getAccessToken(), Duration.ofHours(2)));
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.getWriter().write("{ \"status\": \"success\" }");
                        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8000");
                        response.addHeader("Access-Control-Allow-Credentials", "true");
                    }));
                    config.failureHandler(((HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.setHeader(HttpHeaders.SET_COOKIE, CookieHelper.generateExpiredCookie(OAUTH_COOKIE_NAME));
                        response.getWriter().write("{ \"status\": \"failure\" }");
                        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8000");
                        response.addHeader("Access-Control-Allow-Credentials", "true");
                    }));
                })
                .addFilterBefore(this.customAuthorizationRedirectFilter, OAuth2AuthorizationRequestRedirectFilter.class)
                .exceptionHandling(config -> {
                   config.accessDeniedHandler(this::accessDenied);
                   config.authenticationEntryPoint(this::accessDenied);
                });



        return http.build();
    }

    @SneakyThrows
    private void accessDenied(HttpServletRequest request, HttpServletResponse response, Exception authException) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{ \"error\": \"Access Denied\" }");
    }
}
