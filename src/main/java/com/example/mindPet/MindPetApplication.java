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

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repository, PasswordEncoder encoder) {
        return args -> {
            // OPCIONAL: Limpiar la tabla antes de empezar para que siempre esté fresco
            // repository.deleteAll();

            if (repository.findByCorreo("admin@test.com").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Admin MindPet");
                admin.setCorreo("admin@test.com");

                // IMPORTANTE: El encoder generará el hash compatible con tu sistema
                admin.setContrasena(encoder.encode("admin123"));

                admin.setMonedas(100);
                repository.save(admin);

                System.out.println("-----------------------------------------");
                System.out.println(">>> USUARIO DE PRUEBA CREADO: admin@test.com / admin123");
                System.out.println("-----------------------------------------");
            } else {
                System.out.println(">>> El usuario admin@test.com ya existe, saltando creación.");
            }
        };
    }
}
