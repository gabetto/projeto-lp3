package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Long> {

    public Administrator findByEmail(String email);

    public Administrator findByCpf(String cpf);

    public Administrator findByRg(String rg);
}