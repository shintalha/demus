package com.demus.service;

import com.demus.model.user.User;
import com.demus.model.user.ClientToken;
import com.demus.repository.ClientTokenRepository;
import com.demus.repository.UserRepository;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class CustomAuthorizedClientService implements OAuth2AuthorizedClientService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientTokenRepository clientTokenRepository;

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient client, Authentication authentication) {
        // You can grab the access + refresh tokens as well via the "client"
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        User user = new User(principal.getAttribute("id"), principal.getAttribute("display_name"), principal.getAttribute("images").toString());
        ClientToken clientToken = new ClientToken(principal.getAttribute("id"), client.getAccessToken().getTokenValue(), client.getAccessToken().getExpiresAt().toString(), client.getRefreshToken().getTokenValue(), (long)3600);
        clientTokenRepository.save(clientToken);
        if (userRepository.findById(user.getId()).isEmpty())
            userRepository.save(user);

    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String id) {
        return null;
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

    }

    public ClientToken getClientToken(String id) { //when get the auth token, the data will be deleted.
        ClientToken clientToken = clientTokenRepository.findById(id).orElse(null);
        return clientToken;
    }

}
