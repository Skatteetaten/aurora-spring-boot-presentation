package com.example.demoweb;

import static java.util.List.of;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder.additionalInterceptors(((request, body, execution) -> {
            request.getHeaders().put("custom-header", of("value"));
            return execution.execute(request, body);
        })).build();
    }
}
