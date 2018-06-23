package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.RunResult;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RunResultRepository extends CrudRepository<RunResult, String> {
    List<RunResult> findByRaceId(String id);
}
