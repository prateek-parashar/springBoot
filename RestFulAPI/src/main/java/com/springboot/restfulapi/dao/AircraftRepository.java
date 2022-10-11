package com.springboot.restfulapi.dao;

import com.springboot.restfulapi.models.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
