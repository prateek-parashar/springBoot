package com.springboot.restfulapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetingController {

    @Value("${greeting-name: Mira}")
    private String name;
    @Value("${greeting-coffee : ${greeting-name} is drinking coffee}")
    private String coffee;

    @GetMapping
    public String greet() {
        return name;
    }

    @GetMapping("/coffee")
    public String greetCoffee() {
        return coffee;
    }
}
