package com.JavaRunner.JavaRunner.controller.rest;

import com.JavaRunner.JavaRunner.domain.model.Kit;
import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.model.Ranking;
import com.JavaRunner.JavaRunner.domain.model.Route;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/race")
public class RaceFindable {
    private RaceRepository raceRepository;

    @Autowired
    public RaceFindable(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @GetMapping
    public Iterable<Race> allNewRuns() {
        return raceRepository.findAll();
    }

    @GetMapping(value = "id/{id}")
    public Race oneRace(@PathVariable String id) {
        Optional<Race> byId = raceRepository.findById(id);
        return byId.orElse(null);
    }

    @GetMapping(value = "routes/{id}")
    public Collection<Route> routes(@PathVariable String id) {
        Optional<Race> byId = raceRepository.findById(id);
        return byId.map(Race::getRoutes).orElse(null);
    }

    @GetMapping(value = "rankings/{id}")
    public Collection<Ranking> rankings(@PathVariable String id) {
        Optional<Race> byId = raceRepository.findById(id);
        return byId.map(Race::getRankings).orElse(null);
    }

    @GetMapping(value = "kits/{id}")
    public Collection<Kit> kits(@PathVariable String id) {
        Optional<Race> byId = raceRepository.findById(id);
        return byId.map(Race::getKits).orElse(null);
    }
}
