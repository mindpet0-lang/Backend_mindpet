
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


                String bloqueJuegos =
                        "\n\n--- 🎮 MINIJUEGOS DE BIENESTAR ---\n" +
                                "• 🎈 **El Globo**: Inhala en 4 segundos para inflar tu abdomen, exhala en 6 para desinflarlo.\n" +
                                "• 🔍 **Detective 5-4-3**: Nombra 5 cosas que ves, 4 que oyes y 3 que puedes tocar ahora.\n" +
                                "• ☁️ **Nubes**: Imagina que tus preocupaciones son nubes que se lleva el viento.\n" +
                                "• ✨ **Gratitud**: Dime una sola cosa buena que te haya pasado hoy, por pequeña que sea.";


                String sistema = "Eres MindPet, un asistente de apoyo emocional estilo Pou. " +
                        "Solo hablas de emociones y bienestar. Usa emojis y sé muy dulce. " +
                        "Si preguntan cosas ajenas a la psicología, declina con cariño.";

                try {
                    Map<String, Object> body = Map.of(
                            "contents", List.of(
                                    Map.of("parts", List.of(
                                            Map.of("text", sistema + "\nUsuario: " + prompt)
                                    ))
                            )
                    );

                    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, new HttpHeaders());
                    ResponseEntity<Map> response = restTemplate.postForEntity(URL, entity, Map.class);


                    List candidates = (List) response.getBody().get("candidates");
                    Map content = (Map) ((Map) candidates.get(0)).get("content");
                    List parts = (List) content.get("parts");
                    String respuestaIA = ((Map) parts.get(0)).get("text").toString();

                    if (incluirAviso) {
                        String avisoLegal = "⚠️ *Aviso: Este chat es apoyo emocional y no reemplaza a un profesional.*";
                        return avisoLegal + bloqueJuegos + "\n\n" + respuestaIA;
                    } else {
                        return respuestaIA;
                    }

                } catch (Exception e) {
                    return "¡Hola! Mi sistema está descansando, pero aquí estoy para ti. ¿En qué puedo ayudarte?";
                }
            }
        }