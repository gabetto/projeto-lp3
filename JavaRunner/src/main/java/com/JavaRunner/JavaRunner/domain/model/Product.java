package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends DatabaseCommons {
    @Column(nullable = false, length = 64)
    private String nome;
    @Column(nullable = false, length = 1024)
    private String description;
    @Column(nullable = false, precision = 2)
    private Double price;
    @Column(nullable = false)
    private Integer stock;
    @Column(length = 1024)
    private String notes;
    @ManyToOne
    @JoinColumn
    private Kit kit;

    public Product validate() throws Exception {
        return null;
    }
}
