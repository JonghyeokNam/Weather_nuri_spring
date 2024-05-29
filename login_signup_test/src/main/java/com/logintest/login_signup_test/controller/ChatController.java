package com.logintest.login_signup_test.controller;

import com.logintest.login_signup_test.dto.ChatRequest;
import com.logintest.login_signup_test.dto.ChatResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final WebClient webClient;

    public ChatController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:5000").build();
    }

    @PostMapping("/ask")
    public Mono<String> askQuestion(@RequestBody ChatRequest request) {
        return webClient.post()
                .uri("/ask")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .flatMap(this::handleChatResponse);
    }

    private Mono<String> handleChatResponse(ChatResponse chatResponse) {
        String answer = chatResponse.getAnswer();
        return processAnswer(answer)
                .doOnNext(this::printAnswer); // answer를 출력하는 로직 추가
    }

    private Mono<String> processAnswer(String answer) {
        return Mono.just("Received answer: " + answer);
    }

    private void printAnswer(String answer) {
        // answer 값을 출력합니다.
        System.out.println("Answer received: " + answer);
    }

    /*
    @PostMapping("/answer")
    public Mono<String> receiveAnswer(@RequestBody ChatResponse response) {
        // 여기서 응답을 처리합니다.
        System.out.println("Received answer from Flask: " + response.getAnswer());
        // 필요한 추가 로직을 여기에 추가할 수 있습니다.
        return Mono.just("Received answer: " + response.getAnswer());
    }
    */

    /*
    @PostMapping("/answer")
    public Mono<String> answerQuestion(@RequestBody ChatResponse response) {
        return webClient.post()
                .uri("/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response)
                .retrieve()
                .bodyToMono(String.class);
    }
    */

}

