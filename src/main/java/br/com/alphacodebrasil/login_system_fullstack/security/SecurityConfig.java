package br.com.alphacodebrasil.login_system_fullstack.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        authorizeConfig ->{
                            authorizeConfig.requestMatchers("/log-out").permitAll();
                            authorizeConfig.requestMatchers("/log-in").permitAll();
                            authorizeConfig.requestMatchers("/refresh").permitAll();
                            authorizeConfig.anyRequest().authenticated();

                           }

                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .csrf().disable()
                .build();
    }

}
