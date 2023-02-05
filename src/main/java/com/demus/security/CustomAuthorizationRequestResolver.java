package com.demus.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private static final List<String> PARAMETERS_TO_FORWARD = List.of("connection");

    private final OAuth2AuthorizationRequestResolver delegate;

    private CustomAuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository) {
        this.delegate = new DefaultOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository,
                "/oauth2/authorization"
        );
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        OAuth2AuthorizationRequest defaultRequest = this.delegate.resolve(request);
        return addProperties(defaultRequest, request);
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        OAuth2AuthorizationRequest defaultRequest = this.delegate.resolve(request, clientRegistrationId);
        return addProperties(defaultRequest, request);
    }

    private OAuth2AuthorizationRequest addProperties(OAuth2AuthorizationRequest defaultRequest, HttpServletRequest httpRequest) {
        if (isNull(defaultRequest)) {
            return null;
        }
        Map<String, Object> additionalParameters = new HashMap<>();
        for (String parameterToForward : PARAMETERS_TO_FORWARD) {
            String value = httpRequest.getParameter(parameterToForward);
            if (nonNull(value)) {
                additionalParameters.put(parameterToForward, value);
            }
        }
        return OAuth2AuthorizationRequest.from(defaultRequest)
                .additionalParameters(additionalParameters)
                .build();
    }

}

