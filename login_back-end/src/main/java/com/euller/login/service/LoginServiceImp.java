package com.euller.login.service;

import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;
import java.security.Principal;

@Service
public class LoginServiceImp implements LoginService{

    //Principal = Essa interface representa a noção abstrata de um principal, que pode ser usado para representar
    //qualquer entidade, como um indivíduo, uma corporação e um ID de login (Token)

    private AccessToken getAccessToken(Principal principal){

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;

        // keycloakAuthenticationToken ->
        // KeycloakAuthenticationToken
        // [
        // Principal=9bcb6cee-cce2-4752-9521-626aeb45b859,
        // Credentials=[PROTECTED],
        // Authenticated=true,
        // Details=org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount@5b4093f3,
        // Granted Authorities=[ROLE_user]
        // ]
        //getAccount() -> org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount@5b4093f3
        //getKeycloakSecurityContext() -> org.keycloak.adapters.RefreshableKeycloakSecurityContext@2f8652b1
        //getToken() -> org.keycloak.representations.AccessToken@3f1a7a7b

        return keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();
    }

    @Override
    public String getPreferredUsername(Principal principal){

        //User
        //
        //Usuários são entidades capazes de efetuar login no seu sistema. Eles podem ter atributos associados
        //a eles mesmos, como e-mail, nome de usuário, endereço, número de telefone e dia do nascimento.
        //Eles podem ser associados ao grupo e ter roles específicas atribuídas a eles.

        //user -> getPreferredUsername() - > euller

        return getAccessToken(principal).getPreferredUsername();
    }

}
