package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Race;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RaceRepository extends CrudRepository<Race, String> {
    Optional<Race> findByName(String name);
    List<Race> findAllByOrderByDataDesc();
}
