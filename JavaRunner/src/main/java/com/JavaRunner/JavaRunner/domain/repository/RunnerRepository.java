package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Runner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface RunnerRepository extends CrudRepository<Runner, Long> {
    public Runner findByEmail(String email);
    public Runner findByCpf(String cpf);
    public Runner findByRg(String rg);
}