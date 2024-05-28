package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RootMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class ApiController {
    Logger logger = LoggerFactory.getLogger(ApiController.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Value("${vk.api.access.token}")
    private String vkAccessToken;

    @GetMapping("/")
    public String hello() {
        return "Hello Ngrok!";
    }

    @PostMapping(value = "/new/message", produces = "application/json")
    public String message(@RequestBody String message) {
        try {
            RootMessage rootMessage = mapper.readValue(message, RootMessage.class);
            var messageClass = rootMessage.getObject().getMessage();
            String messageText = messageClass.getText();

            logger.info("Getting message with text: " + messageText);

            var url = UriComponentsBuilder.fromHttpUrl("https://api.vk.com/method/messages.send")
                    .queryParam("user_id", messageClass.getFrom_id())
                    .queryParam("random_id", ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE) + 1)
                    .queryParam("v", rootMessage.getV())
                    .queryParam("message", "Вы сказали: " + messageText)
                    .queryParam("access_token", vkAccessToken)
                    .build().toUri();

            logger.info("Created VK API link");

            var template = new RestTemplate();
            template.getForObject(url, String.class);

            return "OK";
        } catch (JsonProcessingException err) {
            logger.error("JSON is not readable!");
            return "Invalid JSON";
        }
    }
}
