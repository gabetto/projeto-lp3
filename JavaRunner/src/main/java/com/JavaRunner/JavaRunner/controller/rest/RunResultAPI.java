package com.JavaRunner.JavaRunner.controller.rest;

import com.JavaRunner.JavaRunner.domain.model.RunResult;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import com.JavaRunner.JavaRunner.domain.repository.RunResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/run-result")
public class RunResultAPI {

    @Autowired
    RunResultRepository repository;
    @Autowired
    RaceRepository raceRepository;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody RunResult runResult) {
        raceRepository.save(runResult.getRace());
        repository.save(runResult);
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping
    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(repository.findAll());
    }
}
