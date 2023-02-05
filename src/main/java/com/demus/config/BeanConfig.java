package com.demus.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
