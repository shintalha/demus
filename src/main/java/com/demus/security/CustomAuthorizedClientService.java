package com.demus.security;

import com.demus.model.user.User;
import com.demus.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@AllArgsConstructor
public class CustomAuthorizedClientService implements OAuth2AuthorizedClientService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient client, Authentication authentication) {
        // You can grab the access + refresh tokens as well via the "client"
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        User user = new User(principal.getAttribute("id"), principal.getAttribute("display_name"), principal.getAttribute("images").toString(), client.getAccessToken().getTokenValue(), client.getRefreshToken().getTokenValue());
        userRepository.save(user);
    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String id) {
        return null;
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        String a = "";
    }

    public User getClient(String userId) { //when get the auth token, the data will be deleted.
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }



}
