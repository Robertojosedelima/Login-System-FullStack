package br.com.alphacodebrasil.login_system_fullstack.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RefreshService {
    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    private String keycloakIssuerUri;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String clientSecret;

    public ResponseEntity<?> refresh(String refreshToken) {

        RestTemplate restTemplate = new RestTemplate();


        String refreshEndpoint = keycloakIssuerUri + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&grant_type=refresh_token"
                + "&refresh_token=" + refreshToken;

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<Map> response = restTemplate.exchange(refreshEndpoint, HttpMethod.POST, requestEntity, Map.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh failed: " + e.getMessage());
        }

    }
}
