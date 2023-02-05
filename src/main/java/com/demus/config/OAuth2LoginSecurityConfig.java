package com.demus.config;

import com.demus.security.CustomAuthorizationHandler;
import com.demus.security.CustomAuthorizationRedirectFilter;
import com.demus.security.CustomAuthorizationRequestResolver;
import com.demus.security.CustomStatelessAuthorizationRequestRepository;
import com.demus.service.CustomAuthorizedClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfig {
    @Autowired
    CustomStatelessAuthorizationRequestRepository customStatelessAuthorizationRequestRepository;
    @Autowired
    CustomAuthorizationRequestResolver customAuthorizationRequestResolver;
    @Autowired
    CustomAuthorizationRedirectFilter customAuthorizationRedirectFilter;
    @Autowired
    CustomAuthorizedClientService customAuthorizedClientService;
    @Autowired
    CustomAuthorizationHandler customAuthorizationHandler;
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
                    config.successHandler(this.customAuthorizationHandler::successAuth);
                    config.failureHandler(this.customAuthorizationHandler::failureAuth);
                })
                .addFilterBefore(this.customAuthorizationRedirectFilter, OAuth2AuthorizationRequestRedirectFilter.class)
                .exceptionHandling(config -> {
                   config.accessDeniedHandler(this.customAuthorizationHandler::accessDenied);
                   config.authenticationEntryPoint(this.customAuthorizationHandler::accessDenied);
                });
        return http.build();
    }
}
