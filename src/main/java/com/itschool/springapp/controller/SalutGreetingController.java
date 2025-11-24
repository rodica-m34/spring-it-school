package com.itschool.springapp.controller;

import com.itschool.springapp.service.GreetingService;
import com.itschool.springapp.service.impl.GreetingServiceImpl2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalutGreetingController {

    @Qualifier("Salut")
    private final GreetingService greetingService;

    public SalutGreetingController(@Qualifier("Salut") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("greet-salut/{name}")
    public String getGreet(@PathVariable String name) {
        return greetingService.getGreeting(name);
    }
}
