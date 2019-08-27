package com.example.demoweb.b;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greet")
    public GreetingResponse greet() {
        GreetingResponse response = new GreetingResponse();
        response.setGreeting(greetingService.getGreeting());
        return response;
    }
}

class GreetingResponse {

    private String greeting;

    private Instant createdAt = Instant.now();

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
