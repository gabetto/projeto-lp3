package com.jabarunner.JabaRunner.domain.repository;

import com.jabarunner.JabaRunner.domain.model.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<Route, Long> {
    Route findByName(String name);
}
