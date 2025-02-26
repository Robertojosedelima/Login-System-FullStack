package br.com.alphacodebrasil.login_system_fullstack.controller;

import br.com.alphacodebrasil.login_system_fullstack.record.LoginRecord;
import br.com.alphacodebrasil.login_system_fullstack.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/log-in")
public class LoginController {
 @Autowired
 LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRecord loginRecord) {

            return loginService.login(loginRecord.username(), loginRecord.password());

    }
}
