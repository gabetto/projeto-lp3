package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "lots")
@EqualsAndHashCode(callSuper = true)
public class Lot extends DatabaseCommons {
    @Column(nullable = false, length = 20)
    private String initialData;
    @Column(nullable = false, length = 20)
    private String dataFinal;
    @Column(nullable = false, precision = 2)
    private Double discount;
    @ManyToOne(targetEntity = Race.class)
    private Race race;
}
