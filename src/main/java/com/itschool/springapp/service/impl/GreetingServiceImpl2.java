package com.itschool.springapp.service.impl;

import com.itschool.springapp.service.GreetingService;
import org.springframework.stereotype.Service;

@Service ("salut")
public class GreetingServiceImpl2 implements GreetingService {

    @Override
    public String getGreeting(String name) {
        return "Salut "+name;
    }
}
