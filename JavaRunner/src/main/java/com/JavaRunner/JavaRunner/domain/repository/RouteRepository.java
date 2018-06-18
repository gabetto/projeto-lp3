package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<Route, Long> {
    Route findByName(String name);
}
