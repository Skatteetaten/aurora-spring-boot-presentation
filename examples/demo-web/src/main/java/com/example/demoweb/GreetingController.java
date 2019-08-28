package com.example.demoweb;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@RestController
public class GreetingController {

    private GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping("/greet")
    public GreetingResponse hello(@RequestParam(required = false) String name) {

        GreetingResponse response = new GreetingResponse();
        response.setGreeting(greetingService.getGreeting(name));
        return response;
    }


    @RequestMapping("/greeter")
    public void updateGreeter(@Valid @RequestBody UpdateGreeterPayload payload) {

        greetingService.setGreeter(payload.greeter);
    }
}

class GreetingResponse {

    private String greeting;

    private LocalDateTime createdAt = LocalDateTime.now();

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

class UpdateGreeterPayload {

    String greeter;


    @NotNull
    @Pattern(regexp = "[A-z\\s]+")
    public String getGreeter() {

        return greeter;
    }


    public void setGreeter(String greeter) {

        this.greeter = greeter;
    }
}