package com.example.mindPet; // Asegúrate de que coincida con tu paquete

import com.example.mindPet.Model.Usuario;
import com.example.mindPet.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MindPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindPetApplication.class, args);
    }

}
