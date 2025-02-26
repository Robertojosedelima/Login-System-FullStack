package br.com.alphacodebrasil.login_system_fullstack.service;

import org.springframework.http.ResponseEntity;

public interface RefreshService {
    public ResponseEntity<?> refresh(String refreshToken);

}
