package com.JavaRunner.JavaRunner.controller.rest;

import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import com.JavaRunner.JavaRunner.domain.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(value = "/multiples")
public class AggregateRunRanking {
    @Autowired
    RaceRepository races;
    @Autowired
    RankingRepository rankings;

//    @PostMapping
//    public Race aggregate(@RequestBody Race race) {
//        Iterable<Ranking> rankings = this.rankings.saveAll(race.getRankings());
//        race.setRankings((Collection<Ranking>) rankings);
//        return races.save(race);
//}

    @GetMapping
    public Iterable<Race> all() {
        return races.findAll();
    }

    @PostMapping
    public ResponseEntity<?> tryCreate(@RequestBody Race race) {
        HashMap<String, String> errors = race.findErrors();
        Optional<Race> byName = races.findByName(race.getName());
        if (byName.isPresent()) {
            errors.put("exists", "Corrida já existe");
        }
        if (errors.isEmpty()) {
            return ResponseEntity.ok(races.save(race.beautify()));
        }
        return ResponseEntity.status(500).body(errors);
    }
}
