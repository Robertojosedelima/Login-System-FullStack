package br.com.alphacodebrasil.login_system_fullstack.controller;

import br.com.alphacodebrasil.login_system_fullstack.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/log-out")
public class LogoutController {
    @Autowired
    LogoutService logoutService;

    @PostMapping
    public ResponseEntity<?> logout(@RequestParam("refresh_token") String refreshToken) {

        return logoutService.logout(refreshToken);
    }
}
