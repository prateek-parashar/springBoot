package com.springboot.restfulapi.service;

import com.springboot.restfulapi.dao.AircraftRepository;
import com.springboot.restfulapi.models.Aircraft;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PlaneFinderPoller {

    Logger logger = Logger.getLogger(PlaneFinderPoller.class.toString());
    @NonNull
    private AircraftRepository repository;
    @Value("${aircraft-url}")
    private String url;
    WebClient client = WebClient.create("http://localhost:7634/aircraft");


    public Iterable<Aircraft> planePoller() {

        Mono<Aircraft[]> response = client.get()
                                        .retrieve()
                                        .bodyToMono(Aircraft[].class);

        return List.of(response.block());
    }


}






















