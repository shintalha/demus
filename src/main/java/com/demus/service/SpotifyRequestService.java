package com.demus.service;


import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyRequestService {

    public ResponseEntity<String> fetch(String token, String url, HttpMethod httpMethod) {
        ResponseEntity<String> response = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            headers.add("Content-Type", "application/json");
            HttpEntity<String> request = new HttpEntity<String>(headers);
            response = restTemplate.exchange("https://api.spotify.com" + url, httpMethod, request, String.class);
        } catch (Exception ex) {
            response = new ResponseEntity<String>(HttpStatusCode.valueOf(100));
        }
        return response;
    }
}
