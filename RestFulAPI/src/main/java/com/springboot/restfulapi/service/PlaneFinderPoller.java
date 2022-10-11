package com.springboot.restfulapi.service;

import com.springboot.restfulapi.dao.AircraftRepository;
import com.springboot.restfulapi.models.Aircraft;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Supplier;
import java.util.logging.Logger;

@EnableScheduling
@Service
@RequiredArgsConstructor
public class PlaneFinderPoller {

    Logger logger = Logger.getLogger(PlaneFinderPoller.class.toString());
    @NonNull
    private AircraftRepository repository;
    @Value("${aircraft-url}")
    private String url;
    WebClient client = WebClient.create("http://localhost:7634/aircraft");


    @Scheduled(fixedRate = 1000)
    private void planePoller() {

        logger.info("Helllooooooooooo");

        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(repository::save);

        repository.findAll().forEach(System.out::println);

    }


}






















