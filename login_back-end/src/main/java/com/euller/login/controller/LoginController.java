package com.euller.login.controller;

import com.euller.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/user")
    public String getUser(Principal principal) {
        return "Olá "+ loginService.getPreferredUsername(principal)+ " - Keycloak Spring";
    }

    @GetMapping("/admin")
    public String getAdmin(Principal principal) {
        return "Olá " + loginService.getPreferredUsername(principal) + " - Keycloak Spring";
    }

}
