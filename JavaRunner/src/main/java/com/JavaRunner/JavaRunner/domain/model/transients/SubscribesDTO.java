package com.JavaRunner.JavaRunner.domain.model.transients;

import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.model.Runner;
import com.JavaRunner.JavaRunner.domain.model.SubscribeRace;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import com.JavaRunner.JavaRunner.domain.repository.RunnerRepository;
import com.JavaRunner.JavaRunner.domain.repository.SubscribeRaceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SubscribesDTO {
    @Autowired
    RunnerRepository runnerRepository;
    @Autowired
    RaceRepository raceRepository;
    @Autowired
    SubscribeRaceRepository repository;
    private String runnerId;
    private String raceId;
    private Double price;
    private Collection<Runner> runners;
    private Race race;
    private List<SubscribeRace> subscribes;

    public SubscribesDTO(String raceId) {
        this.raceId = raceId;
        repository.findByRaceId(raceId);
    }

    public List<Runner> getRunners() {
        List<Runner> runners = new ArrayList<>();
        for (SubscribeRace subs : this.subscribes) {
            Optional<Runner> byId = runnerRepository.findById(subs.getRunnerId());
            byId.ifPresent(runners::add);
        }
        return runners;
    }

    public Optional<Race> getRace() {
        return raceRepository.findById(this.raceId);
    }
}
