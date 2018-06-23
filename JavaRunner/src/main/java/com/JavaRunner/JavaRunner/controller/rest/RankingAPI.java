package com.JavaRunner.JavaRunner.controller.rest;

import com.JavaRunner.JavaRunner.domain.model.Ranking;
import com.JavaRunner.JavaRunner.domain.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/ranking")
public class RankingAPI {

    @Autowired
    RankingRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<?> rankingClassification(@PathVariable String id) {
        Optional<Ranking> ranking = repository.findById(id);
        if (!ranking.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ranking);
    }

}
