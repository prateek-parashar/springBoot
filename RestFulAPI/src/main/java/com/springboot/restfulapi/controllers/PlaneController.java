package com.springboot.restfulapi.controllers;

import com.springboot.restfulapi.service.PlaneFinderPoller;
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
    private final PlaneFinderPoller poller;
    private WebClient client =
            WebClient.create("http://localhost:7634/aircraft");

    @GetMapping("/aircraft")
    public String getCurrentAircraftPositions(Model model) {
        model.addAttribute("currentPositions", poller.planePoller());
        return "positions";
    }

}
