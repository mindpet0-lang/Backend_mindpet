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
    public Map<String, String> sendMessage(@RequestBody Map<String, String> payload) {
        String userPrompt = payload.get("text");


        long totalMensajes = messageRepository.count();


        boolean esPrimerMensaje = (totalMensajes == 0);


        messageRepository.save(new Message(userPrompt, "USER"));


        String aiResponse = geminiService.consultarIA(userPrompt, esPrimerMensaje);

        messageRepository.save(new Message(aiResponse, "AI"));

        Map<String, String> response = new HashMap<>();
        response.put("reply", aiResponse);
        return response;
    }

    @GetMapping("/history")
    public List<Message> getHistory() {
        return messageRepository.findAll();
    }
}