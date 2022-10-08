package com.springboot.restfulapi.config;

import com.springboot.restfulapi.dao.CoffeeRepository;
import com.springboot.restfulapi.models.Coffee;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoader {
    private final CoffeeRepository dao;

    DataLoader(CoffeeRepository dao) {
        this.dao = dao;

    }

    @PostConstruct
    private void loadInitalData() {
        this.dao.saveAll(List.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Três Pontas")
        ));
    }
}
