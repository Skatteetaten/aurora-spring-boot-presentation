package com.example.demoweb.a;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    @GetMapping("/greet")
    public @ResponseBody
    String greet() {
        return "Hello, world";
    }
}