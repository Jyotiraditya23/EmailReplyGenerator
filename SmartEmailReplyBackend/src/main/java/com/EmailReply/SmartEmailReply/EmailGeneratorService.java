package com.EmailReply.SmartEmailReply;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiURl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateResponse(@RequestBody EmailRequest emailRequest){
        //Build Prompt
        String prompt = buildPrompt(emailRequest);

        //Craft request
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts",List.of(
                                        Map.of("text",prompt)
                                )
                        )
                )
        );
        System.out.println("Calling URL: " + geminiApiURl + "?key=" + geminiApiKey);


        //Do request and get Response

        String Response = webClient.post()
                .uri(geminiApiURl+"?key="+geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        //return response
        return extractResponseContent(Response);
    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        }catch (Exception e){
            return "Error in extracting response" + e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a proffessional response for the provided mail");
        if(emailRequest.getTone()!=null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a").append(emailRequest.getTone()).append(" tone");
        }
        prompt.append("\nOriginal Email\n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
