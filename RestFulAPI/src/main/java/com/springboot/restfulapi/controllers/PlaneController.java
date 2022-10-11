package com.springboot.restfulapi.controllers;

import com.springboot.restfulapi.dao.AircraftRepository;
import com.springboot.restfulapi.models.Aircraft;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Controller
public class PlaneController {

    @NonNull
    private final AircraftRepository repository;
    private WebClient client =
            WebClient.create("http://localhost:7634/aircraft");

    @GetMapping("/aircraft")
    public String getCurrentAircraftPositions(Model model) {
        repository.deleteAll();

        client.get()
              .retrieve()
              .bodyToFlux(Aircraft.class)
              .filter(plane -> !plane.getReg().isEmpty())
              .toStream()
              .forEach(repository::save);

        model.addAttribute("currentPositions", repository.findAll());
        return "positions";
    }

}
