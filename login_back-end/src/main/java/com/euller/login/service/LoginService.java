package com.euller.login.service;
import java.security.Principal;

public interface LoginService {

    String getPreferredUsername(Principal principal);

}
