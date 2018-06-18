package com.JavaRunner.JavaRunner.domain.repository;

import com.JavaRunner.JavaRunner.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
