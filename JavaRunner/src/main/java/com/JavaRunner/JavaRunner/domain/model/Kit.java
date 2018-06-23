package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
public class Kit extends DatabaseCommons implements Serializable {
    @Column(nullable = false, length = 64)
    private String type;
    @Column(nullable = false, length = 64)
    private String name;
    @Column(length = 1024)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Race.class)
    private Race race;
    @OneToMany(mappedBy = "kit", targetEntity = Product.class)
    private Collection<Product> products;
}
