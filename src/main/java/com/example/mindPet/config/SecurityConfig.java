package com.example.mindPet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // 🔐 Encriptador de contraseñas
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    // 🔒 Configuración de seguridad
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/usuarios/**").permitAll() // Permite login y registro
                        .requestMatchers("/api/chat/**").permitAll() // <--- ESTA ES LA LÍNEA QUE TE FALTA
                        .requestMatchers("/diarios/**").permitAll()

                        //lo q debe ir =
                        //.requestMatchers("/usuarios/login", "/usuarios/register").permitAll()
                        //pero por ahora permite todos los endpoints, sin embargo despues cambio esto pero con mas tiempo
                        .requestMatchers("/**").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // 🔥 CLAVE
                .build();
    }

    // 🌐 Configuración CORS (IMPORTANTE para Angular)
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // ⚠️ en producción cambia esto
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}