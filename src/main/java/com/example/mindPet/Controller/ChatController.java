package com.example.mindPet.Controller;

import com.example.mindPet.Model.Message;
import com.example.mindPet.Repository.MessageRepository;
import com.example.mindPet.Service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Map<String, Object> payload) {
        if (payload.get("text") == null || payload.get("userId") == null) {
            return ResponseEntity.badRequest().body("Error: Faltan los campos 'text' o 'userId'.");
        }

        String userPrompt = payload.get("text").toString();
        Long userId = Long.valueOf(payload.get("userId").toString());

        // 👉 Guardar mensaje del usuario
        messageRepository.save(new Message(userPrompt, "USER", userId));

        // 👉 Obtener historial ORDENADO
        List<Message> historial = messageRepository.findByUserIdOrderByTimestampAsc(userId);

        // 👉 Detectar primer mensaje
        boolean esPrimerMensaje = (historial.size() <= 1);

        // 👉 Llamar IA con historial
        String aiResponse = geminiService.consultarIA(historial, userPrompt, esPrimerMensaje);

        // 👉 Guardar respuesta IA
        messageRepository.save(new Message(aiResponse, "AI", userId));

        Map<String, String> response = new HashMap<>();
        response.put("reply", aiResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{userId}")
    public List<Message> getHistory(@PathVariable Long userId) {
        return messageRepository.findByUserId(userId);
    }
}