package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Ranking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends CrudRepository<Ranking, Long> {
}
