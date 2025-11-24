package com.itschool.springapp.service.impl;

import com.itschool.springapp.service.GreetingService;
import org.springframework.stereotype.Service;

@Service("hello")
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String getGreeting(String name) {
        return "Hello " + name;
    }
}
