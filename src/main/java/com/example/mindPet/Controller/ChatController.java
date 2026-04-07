package com.example.mindPet.Controller;

import com.example.mindPet.Model.Message;
import com.example.mindPet.Repository.MessageRepository;
import com.example.mindPet.Service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String, String> sendMessage(@RequestBody Map<String, Object> payload) {
        String userPrompt = (String) payload.get("text");

        // Obtenemos el ID del usuario enviado desde Flutter
        Long userId = Long.valueOf(payload.get("userId").toString());

        // CAMBIO CLAVE: Contamos mensajes SOLO de este usuario
        long mensajesDelUsuario = messageRepository.countByUserId(userId);

        // Si es su primer mensaje, esPrimerMensaje será true
        boolean esPrimerMensaje = (mensajesDelUsuario == 0);

        // Guardamos el mensaje del usuario con su ID
        messageRepository.save(new Message(userPrompt, "USER", userId));

        // Consultamos a Gemini pasando el booleano para el aviso legal
        String aiResponse = geminiService.consultarIA(userPrompt, esPrimerMensaje);

        // Guardamos la respuesta de la IA con el ID del usuario
        messageRepository.save(new Message(aiResponse, "AI", userId));

        Map<String, String> response = new HashMap<>();
        response.put("reply", aiResponse);
        return response;
    }

    // Historial filtrado para que el Usuario A no vea los mensajes del Usuario B
    @GetMapping("/history/{userId}")
    public List<Message> getHistory(@PathVariable Long userId) {
        return messageRepository.findByUserId(userId);
    }
}