package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Race;
import org.springframework.data.repository.CrudRepository;

public interface RaceRepository extends CrudRepository<Race, Long> {
    public Race findByName(String name);
}
