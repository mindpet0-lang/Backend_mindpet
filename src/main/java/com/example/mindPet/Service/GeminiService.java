
        package com.example.mindPet.Service;


        import com.example.mindPet.Model.Message;
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
            public String consultarIA(List<Message> historial, String prompt, boolean incluirAviso) {

                RestTemplate restTemplate = new RestTemplate();

                try {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    StringBuilder contentsBuilder = new StringBuilder();
                    contentsBuilder.append("[");

                    // 🔹 INSTRUCCIÓN INICIAL (SIMULA SYSTEM)
                    contentsBuilder.append("{\"role\":\"user\",\"parts\":[{\"text\":\"")
                            .append("Eres MindPet, una mascota psicológica. ")
                            .append("NO saludes en cada mensaje. ")
                            .append("Solo saluda al inicio. ")
                            .append("Responde como una conversación continua. ")
                            .append("No repitas avisos innecesarios.")
                            .append("\"}]},");

                    // 🔹 LIMITAR HISTORIAL (últimos 10 mensajes)
                    int start = Math.max(0, historial.size() - 10);
                    List<Message> historialLimitado = historial.subList(start, historial.size());

                    // 🔹 HISTORIAL
                    for (Message msg : historialLimitado) {
                        String role = msg.getSender().equals("USER") ? "user" : "model";

                        contentsBuilder.append("{\"role\":\"")
                                .append(role)
                                .append("\",\"parts\":[{\"text\":\"")
                                .append(msg.getContent().replace("\"", "'"))
                                .append("\"}]},");
                    }

                    // 🔹 NUEVO MENSAJE
                    contentsBuilder.append("{\"role\":\"user\",\"parts\":[{\"text\":\"")
                            .append(prompt.replace("\"", "'"))
                            .append("\"}]}");

                    contentsBuilder.append("]");

                    String jsonBody = "{ \"contents\": " + contentsBuilder.toString() + " }";

                    HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

                    ResponseEntity<Map> response = restTemplate.postForEntity(getUrl(), entity, Map.class);

                    List candidates = (List) response.getBody().get("candidates");
                    Map firstCandidate = (Map) candidates.get(0);
                    Map content = (Map) firstCandidate.get("content");
                    List parts = (List) content.get("parts");

                    String respuestaIA = ((Map) parts.get(0)).get("text").toString();

                    return respuestaIA;

                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                    return "Lo siento no pude procesar eso repitelo una vez mas por favor" ;
                }
            }
        }