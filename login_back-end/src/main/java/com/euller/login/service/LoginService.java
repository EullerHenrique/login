package com.euller.login.service;
import java.security.Principal;

public interface LoginService {

    public String getPreferredUsername(Principal principal);

}
