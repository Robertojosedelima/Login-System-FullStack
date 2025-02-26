package br.com.alphacodebrasil.login_system_fullstack.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/testessl")
public class TesteSSL {
   @GetMapping
    public String testeSSL(){
        return "teste SSL";
    }
}
