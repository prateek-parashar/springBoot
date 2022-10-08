package com.springboot.restfulapi.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Coffee {
    public Coffee() {

    }

    public Coffee(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    @Id
    private String id;
    private String name;

}
