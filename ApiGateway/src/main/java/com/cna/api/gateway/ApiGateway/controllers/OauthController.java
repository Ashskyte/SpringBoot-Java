package com.cna.api.gateway.ApiGateway.controllers;

import com.cna.api.gateway.ApiGateway.models.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class OauthController {
    private final Logger logger = LoggerFactory.getLogger(OauthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient oauthAuthorizedClient,
                                              @AuthenticationPrincipal OidcUser user, Model model

    ) {

        List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
            return grantedAuthority.getAuthority();
        }).toList();
        logger.info("User: {}", user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(user.getEmail());
        authResponse.setAccessToken(oauthAuthorizedClient.getAccessToken().getTokenValue());
        authResponse.setRefreshToken(oauthAuthorizedClient.getRefreshToken().getTokenValue());
        authResponse.setAuthorities(authorities);
        authResponse.setExpiresAt(oauthAuthorizedClient.getAccessToken().getExpiresAt().toEpochMilli());
        return ResponseEntity.ok(authResponse);
    }
}
