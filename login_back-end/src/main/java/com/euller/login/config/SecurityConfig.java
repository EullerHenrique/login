package com.euller.login.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

//@KeycloakConfiguration = Configuração do keycloak
//-> Adicione esta anotação a uma classe que se estende KeycloakWebSecurityConfigurerAdapter
//para fornecer uma configuração de segurança Spring baseada em keycloak.

//KeyCloakSecurityConfigureAdapter = Adaptador do Configurador de Segurança Web Keycloak
//-> Fornece uma classe base conveniente para criar uma WebSecurityConfigurer instância protegida pelo Keycloak.
//Essa implementação permite a personalização por métodos de substituição.

@KeycloakConfiguration
class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    //Define as configurações de autenticação
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {

        //AuthenticationManagerBuilder = Construtor do Gerenciador de Autenticação

        //Cria o provedor de autenticação do keycloak
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();

        //Prefixa as roles com ROLE_
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());

        //Define que o provedor de autenticação é o provedor de autenticação do keycloak
        auth.authenticationProvider(keycloakAuthenticationProvider);

    }

    // Salva o usuário em uma sessão após o login
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {

        // SessionAuthenticationStrategy = Estratégia de autenticação de sessão
        // -> Permite suporte conectável para comportamento relacionado a HttpSession quando ocorre uma autenticação.
        // -> O uso típico seria certificar-se de que existe uma sessão ou alterar o ID da sessão para se proteger
        // contra ataques de fixação de sessão.

        // RegisterSessionAuthenticationStrategy = Estrátegia de autenticação de sessão de registro
        // -> Estratégia utilizada para cadastrar um usuário com o SessionRegistry após uma autenticação

        // SessionRegistryImpl = Implementação do Registro de Sessão
        // -> Implementação padrão do Registro de Sessão que escuta SessionDestroyedEvents publicados
        // no contexto do aplicativo Spring.

        // SessionDestroyedEvent = Eventos de Sessão Destruída
        // -> Evento genérico de "encerramento de sessão" que indica que uma sessão
        // (potencialmente representada por um contexto de segurança) foi encerrada.

        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    //Define configurações de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                //O usuário associado à role user pode acessar o endpoint /user
                .antMatchers("/user").hasRole("user")
                //O usuário associado à role admin pode acessar o endpoint /admin
                .antMatchers("/admin").hasRole("admin");

    }

}

