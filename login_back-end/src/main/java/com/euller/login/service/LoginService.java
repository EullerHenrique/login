package com.euller.login.service;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class LoginService {

    private AccessToken getAccessToken(Principal principal){
        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        return keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();
    }

    public String getPreferredUsername(Principal principal){
        return getAccessToken(principal).getPreferredUsername();
    }

}
