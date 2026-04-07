
        package com.example.mindPet.Service;


        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.http.HttpEntity;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Service;
        import org.springframework.web.client.RestTemplate;

        import java.util.List;
        import java.util.Map;

        @Service
        public class GeminiService {

            @Value("${gemini.api.key}")
            private String apiKey;

            private String getUrl() {
                return "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;
            }
            public String consultarIA(String prompt, boolean incluirAviso) {
                RestTemplate restTemplate = new RestTemplate();

                // Bloques de texto fijos
                String avisoLegal = "⚠️ *AVISO LEGAL: Apoyo emocional, no reemplaza a un profesional.*";
                String bloqueJuegos = "\n\n--- 🎮 JUEGOS DE BIENESTAR ---\n" +
                        "• 🎈 **El Globo**: Respira profundo.\n" +
                        "• 🔍 **Detective**: 5 cosas que ves.\n" +
                        "• ✨ **Gratitud**: Dime algo bueno de hoy.";

                try {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    // ESTRUCTURA JSON SIMPLIFICADA (Compatible con 2.5 y 3.0)
                    String jsonBody = "{"
                            + "\"contents\": [{"
                            + "  \"parts\": [{\"text\": \"Eres MindPet, un asistente dulce. Solo hablas de emociones. Usuario dice: " + prompt + "\"}]"
                            + "}]"
                            + "}";

                    HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

                    // Petición POST
                    ResponseEntity<Map> response = restTemplate.postForEntity(getUrl(), entity, Map.class);

                    // Extracción segura de la respuesta
                    List candidates = (List) response.getBody().get("candidates");
                    Map firstCandidate = (Map) candidates.get(0);
                    Map content = (Map) firstCandidate.get("content");
                    List parts = (List) content.get("parts");
                    String respuestaIA = ((Map) parts.get(0)).get("text").toString();

                    if (incluirAviso) {
                        return avisoLegal + bloqueJuegos + "\n\n" + respuestaIA;
                    }
                    return respuestaIA;

                } catch (Exception e) {
                    // ESTO ES VITAL: Mira tu consola de IntelliJ para ver el error real
                    System.err.println("--- ERROR CRÍTICO ---");
                    System.err.println("Mensaje: " + e.getMessage());

                    // Si el error es 400, es la API Key o el nombre del modelo
                    return "Error técnico: " + e.getMessage();
                }
            }
        }