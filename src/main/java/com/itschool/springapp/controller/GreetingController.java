package com.itschool.springapp.controller;

import com.itschool.springapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(@Qualifier ("hello") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("greet/{name}")
    public String getGreet(@PathVariable String name) {
        return greetingService.getGreeting(name);
    }
}
