package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Kit;
import com.JavaRunner.JavaRunner.domain.model.Race;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KitRepository extends CrudRepository<Kit, String> {
    List<Kit> findAllByRace(Race race);
}
