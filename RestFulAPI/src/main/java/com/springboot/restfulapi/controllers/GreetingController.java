package com.springboot.restfulapi.controllers;

import com.springboot.restfulapi.models.Greeting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetingController {

    private Greeting greeting;
    public GreetingController(Greeting greeting) {
        this.greeting = greeting;
    }

    @GetMapping
    public String greet() {
        return greeting.getName();
    }

    @GetMapping("/coffee")
    public String greetCoffee() {
        return greeting.getCoffeeGreeting();
    }
}
