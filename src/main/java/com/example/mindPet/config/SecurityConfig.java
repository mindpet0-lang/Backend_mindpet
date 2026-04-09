package com.example.mindPet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // OBLIGATORIO para que funcionen los POST de Flutter
                .cors(Customizer.withDefaults()) // OBLIGATORIO para que Chrome no bloquee la petición
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuarios/**").permitAll() // Permite login y registro
                        .requestMatchers("/api/chat/**").permitAll() // <--- ESTA ES LA LÍNEA QUE TE FALTA
                        .requestMatchers("/diarios/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }


}