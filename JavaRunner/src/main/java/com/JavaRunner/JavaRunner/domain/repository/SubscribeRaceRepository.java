package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.SubscribeRace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeRaceRepository extends CrudRepository<SubscribeRace, String> {
    List<SubscribeRace> findByRaceId(String raceId);
}
