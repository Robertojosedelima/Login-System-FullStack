package br.com.alphacodebrasil.login_system_fullstack.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRecord(@NotBlank(message = "Obrigatorio informar Usuario. ")
                          @Size(min = 6, max = 20, message ="Nome do usuario deve ter no minimo 6 caracteres e no maximo 20 caracteres . ")
                          String username,
                          @NotBlank(message = "Obrigatorio informar Senha. ")
                          @Size(min = 6, max = 64, message ="A Senha deve ter no minimo 6 caracteres e no maximo 64 caracteres . ")
                          String password

) {
}
