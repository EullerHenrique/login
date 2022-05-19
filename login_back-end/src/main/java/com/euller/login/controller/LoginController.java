package com.euller.login.controller;

import com.euller.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/user")
    public String getUser(Principal principal) {
        return "Olá "+ loginService.getPreferredUsername(principal)+ " - Keycloak Spring";
    }

    @GetMapping("/admin")
    public String getAdmin(Principal principal) {
        return "Olá " + loginService.getPreferredUsername(principal) + " - Keycloak Spring";
    }

}
