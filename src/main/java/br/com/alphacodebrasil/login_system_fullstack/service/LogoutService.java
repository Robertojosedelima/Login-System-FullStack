package br.com.alphacodebrasil.login_system_fullstack.service;

import org.springframework.http.ResponseEntity;

public interface LogoutService {
    public ResponseEntity<?> logout(String refreshToken);
}
