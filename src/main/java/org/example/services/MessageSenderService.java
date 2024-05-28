package org.example.services;

import org.example.dto.NewMessageObject;
import org.example.dto.RootMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MessageSenderService {
    private final Set<Integer> randomIdHistory = new LinkedHashSet<>();

    @Value("${vk.api.access.token}")
    private String vkAccessToken;

    public void sendMessage(RootMessage rootMessage) {
        NewMessageObject messageObject = rootMessage.getObject();
        String messageText = messageObject.getMessage().getText();

        WebClient.create("https://api.vk.com/method").get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("messages.send")
                                .queryParam("user_id", messageObject.getMessage().getFrom_id())
                                .queryParam("random_id", getRandomId())
                                .queryParam("v", rootMessage.getV())
                                .queryParam("message", "Вы сказали: " + messageText)
                                .queryParam("access_token", vkAccessToken)
                                .build())
                .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            error -> Mono.error(new RuntimeException("API is not found")))
                    .onStatus(HttpStatusCode::is5xxServerError,
                            error -> Mono.error(new RuntimeException("Server is not responding")));
    }

    private int getRandomId() {
        int randomId;

        do {
            randomId = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE) + 1;
        } while (randomIdHistory.contains(randomId));

        if (randomIdHistory.size() > 1000) {
            randomIdHistory.remove(randomIdHistory.iterator().next());
        }

        return randomId;
    }
}
