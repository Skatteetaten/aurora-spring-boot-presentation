package com.example.demowebcache;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Cacheable("greeting")
    public GreetingResponse greet(@RequestParam(required = false) String name) throws InterruptedException {

        Thread.sleep(3000);
        GreetingResponse response = new GreetingResponse();
        response.setGreeting(greetingService.getGreeting(name));
        return response;
    }

    @RequestMapping("/greeter")
    @CacheEvict(cacheNames = "greeting", allEntries = true)
    public void updateGreeter(@Valid @RequestBody UpdateGreeterPayload payload) {

        this.greetingService.setGreeter(payload.name);
    }
}

class GreetingResponse /* implements Serializable */ {

    private String greeting;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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

    String name;

    @NotNull
    @Pattern(regexp = "[A-z\\s]+")
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}