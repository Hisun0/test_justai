package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RootMessage;
import org.example.services.MessageSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    Logger logger = LoggerFactory.getLogger(ApiController.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MessageSenderService messageSenderService;

    @GetMapping("/")
    public String hello() {
        return "Hello Ngrok!";
    }

    @PostMapping(value = "/new/message", produces = "application/json")
    public String message(@RequestBody String message) {
        try {
            RootMessage rootMessage = mapper.readValue(message, RootMessage.class);
            String messageText = rootMessage.getObject().getMessage().getText();
            logger.info("Getting message with text: " + messageText);

            messageSenderService.sendMessage(rootMessage);
            logger.info("Message sent!");

            return "OK";
        } catch (JsonProcessingException err) {
            logger.error("JSON is not readable!");
            return "Invalid JSON";
        }
    }
}
