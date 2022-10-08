package com.springboot.restfulapi.controllers;

import com.springboot.restfulapi.dao.CoffeeRepository;
import com.springboot.restfulapi.models.Coffee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeRepository dao;

    public CoffeeController(CoffeeRepository dao) {
        this.dao = dao;
    }

    @GetMapping
    Iterable<Coffee> getCoffeeList() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return dao.findById(id);
    }

    @PostMapping
    Coffee addCoffee(@RequestBody Coffee coffee) {
        return dao.save(coffee);
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        return (dao.existsById(id) ? new ResponseEntity<>(dao.save(coffee), HttpStatus.OK) : new ResponseEntity<>(dao.save(coffee), HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        dao.deleteById(id);
    }

}
