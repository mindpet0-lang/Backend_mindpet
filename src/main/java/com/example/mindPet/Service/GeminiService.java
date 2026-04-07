
        package com.example.mindPet.Service;

        import org.springframework.http.HttpEntity;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Service;
        import org.springframework.web.client.RestTemplate;

        import java.util.List;
        import java.util.Map;

        @Service
        public class GeminiService {

            private final String API_KEY = "AIzaSyBJl8iFk_yrPIYMga3oENBYtM_WzV3zPso".trim();
            private final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

            public String consultarIA(String prompt, boolean incluirAviso) {
                RestTemplate restTemplate = new RestTemplate();

            
                String instrucciones = "Eres MindPet, un asistente de apoyo emocional. " +
                        "REGLA 1: Solo puedes responder sobre psicología, emociones y bienestar. " +
                        "Si el usuario pregunta sobre juegos, cocina, programación o cualquier otro tema, responde: 'Lo siento, como tu MindPet solo puedo apoyarte en temas emocionales y bienestar.' " +
                        (incluirAviso ? "REGLA 2: Inicia OBLIGATORIAMENTE con el aviso legal: 'Este chat es una herramienta de apoyo emocional y no reemplaza la ayuda de un profesional...' " : "REGLA 2: NO incluyas el aviso legal legal de nuevo. ");

                try {
                    Map<String, Object> body = Map.of(
                            "contents", List.of(
                                    Map.of("parts", List.of(
                                            Map.of("text", instrucciones + "\nUsuario: " + prompt)
                                    ))
                            )
                    );

                    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, new HttpHeaders());
                    ResponseEntity<Map> response = restTemplate.postForEntity(URL, entity, Map.class);

                    List candidates = (List) response.getBody().get("candidates");
                    Map content = (Map) ((Map) candidates.get(0)).get("content");
                    List parts = (List) content.get("parts");

                    return ((Map) parts.get(0)).get("text").toString();

                } catch (Exception e) {
                    return "Lo siento, tuve un problema interno.";
                }
            }
        }