package com.JavaRunner.JavaRunner.controller.rest;

import com.JavaRunner.JavaRunner.controller.requests.SubscribeRequest;
import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.model.Runner;
import com.JavaRunner.JavaRunner.domain.model.SubscribeRace;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import com.JavaRunner.JavaRunner.domain.repository.RunnerRepository;
import com.JavaRunner.JavaRunner.domain.repository.SubscribeRaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/subscribe")
public class SubscribeAPI {
    @Autowired
    SubscribeRaceRepository repository;
    @Autowired
    RunnerRepository runners;
    @Autowired
    RaceRepository raceRepository;

    @PostMapping
    public ResponseEntity<?> subscribe(@RequestBody SubscribeRequest subscribe) {
        if (this.runners.existsById(subscribe.getRunnerId()) && raceRepository.existsById(subscribe.getRaceId())) {
            SubscribeRace subs = new SubscribeRace()
                    .setDoPayment(subscribe.getDoPayment())
                    .setPrice(subscribe.getPrice())
                    .setRaceId(subscribe.getRaceId())
                    .setRunnerId(subscribe.getRunnerId());
            return ResponseEntity.ok(repository.save(subs));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAll(@PathVariable String id) {
        Optional<Race> race = raceRepository.findById(id);
        List<SubscribeRequest> allSubs = new ArrayList<>();
        for (SubscribeRace subs : repository.findByRaceId(id)) {
            Optional<Runner> runner = this.runners.findById(subs.getRaceId());
            if (runner.isPresent() && race.isPresent()) {
                SubscribeRequest unique = new SubscribeRequest()
                        .setDoPayment(subs.getDoPayment())
                        .setPrice(subs.getPrice())
                        .setRace(race.get())
                        .setRunner(runner.get());
                allSubs.add(unique);
            }
        }
        return ResponseEntity.ok(allSubs);
    }
}
