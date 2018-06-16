package com.jabarunner.JabaRunner.domain.repository;

import com.jabarunner.JabaRunner.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
