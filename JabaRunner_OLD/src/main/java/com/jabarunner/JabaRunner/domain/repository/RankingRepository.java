package com.jabarunner.JabaRunner.domain.repository;

import com.jabarunner.JabaRunner.domain.model.Ranking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends CrudRepository<Ranking, Long> {
}
