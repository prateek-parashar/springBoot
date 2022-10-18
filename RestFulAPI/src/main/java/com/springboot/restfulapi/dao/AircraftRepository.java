package com.springboot.restfulapi.dao;

import com.springboot.restfulapi.models.Aircraft;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends ReactiveCrudRepository<Aircraft, Long> {
}
