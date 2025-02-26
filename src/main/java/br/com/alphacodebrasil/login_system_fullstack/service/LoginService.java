package br.com.alphacodebrasil.login_system_fullstack.service;

import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> login(String username, String password);
}
