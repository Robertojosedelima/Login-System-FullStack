package br.com.alphacodebrasil.login_system_fullstack;

import br.com.alphacodebrasil.login_system_fullstack.encrypted.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LoginSystemFullstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginSystemFullstackApplication.class, args);

		PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
		String password = "123456";
		String hashedPassword = passwordEncryptor.hashPassword(password);

		System.out.println("Senha original: " + password);
		System.out.println("Senha criptografada: " + hashedPassword);

		// Verificando se a senha corresponde ao hash
		System.out.println("Senha válida? " + passwordEncryptor.verifyPassword(password, hashedPassword));

	}
}

