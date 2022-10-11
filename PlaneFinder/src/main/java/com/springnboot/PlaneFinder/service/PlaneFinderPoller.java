package com.springnboot.PlaneFinder.service;

import com.springnboot.PlaneFinder.domain.Aircraft;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
public class PlaneFinderPoller {

    private WebClient webClient = WebClient.create("${aircraft-url}");

    private final RedisConnectionFactory connectionFactory;
    private final RedisOperations<String, Aircraft> redisOperations;

    public PlaneFinderPoller(RedisConnectionFactory connectionFactory, RedisOperations<String, Aircraft> redisTemplate) {
        this.connectionFactory = connectionFactory;
        this.redisOperations = redisTemplate;
    }

    @Scheduled(fixedRate = 1000)
    private void pollPlanes() {
        // Remove the current data inside the database
        this.connectionFactory.getConnection().serverCommands().flushDb();

        webClient.get()
                 .retrieve()
                 .bodyToFlux(Aircraft.class)
                 .filter(plane -> plane.getReg().isEmpty())
                 .toStream()
                 .forEach(plane -> redisOperations.opsForValue().set(plane.getReg(), plane));

        redisOperations.opsForValue()
                       .getOperations()
                       .keys("*")
                       .forEach(plane -> System.out.println(redisOperations.opsForValue().get(plane)));

    }

}


























