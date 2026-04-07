package com.example.mindPet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // OBLIGATORIO para APIs
                .cors(Customizer.withDefaults()) // Permite peticiones desde Flutter/Chrome
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuarios/**").permitAll() // Permite el login y registro
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}