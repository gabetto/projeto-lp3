package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.model.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepository extends CrudRepository<Route, String> {
    Route findByName(String name);

    List<Route> findAllByRace(Race race);
}
