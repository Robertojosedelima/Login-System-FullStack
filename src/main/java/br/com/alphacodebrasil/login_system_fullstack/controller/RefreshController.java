package br.com.alphacodebrasil.login_system_fullstack.controller;

import br.com.alphacodebrasil.login_system_fullstack.service.RefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/refresh")
public class RefreshController {

    @Autowired
    RefreshService refreshService;

    @PostMapping
    public ResponseEntity<?> refresh(@RequestParam String refreshToken){

        return refreshService.refresh(refreshToken);

    }
}
