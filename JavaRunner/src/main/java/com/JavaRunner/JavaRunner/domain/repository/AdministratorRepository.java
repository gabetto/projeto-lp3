package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, String> {

    Administrator findByEmail(String email);

    Administrator findByCpf(String cpf);

    Optional<Administrator> findByLoginAndPassword(String login, String password);

    Administrator findByRg(String rg);

}