package com.springboot.restfulapi.controllers;

import com.springboot.restfulapi.models.Coffee;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CoffeeController {
    private List<Coffee> coffees = new ArrayList<>();

    public CoffeeController() {
        coffees.addAll(List.of(new Coffee("Café Cereza"), new Coffee("Café Ganador"), new Coffee("Café Lareño"), new Coffee("Café Três Pontas")));
    }

    @GetMapping("/coffees")
    Iterable<Coffee> getCoffeeList() {
        return coffees;
    }

    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return coffees.stream()
                      .filter(coffee -> coffee.getId().equals(id))
                      .findAny();
    }

    @PostMapping("/coffees")
    Coffee addCoffee(@RequestBody Coffee coffee) {
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/coffees")
    Optional<Coffee> putCoffee(@RequestBody Coffee coffee) {
        Optional<Coffee> availableCoffee = coffees.stream()
                                                  .filter(c -> c.getId().equals(coffee.getId()))
                                                  .findAny();
        return Optional.ofNullable(availableCoffee.orElse(addCoffee(coffee)));
    }

    @DeleteMapping("/coffees/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(coffee -> coffee.getId().equals(id));
    }

}
