package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "lote")
public class Lot {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "data_inicial", nullable = false, length = 20)
    private String dataInicial;
    @Basic
    @Column(name = "data_final", nullable = false, length = 20)
    private String dataFinal;
    @Basic
    @Column(name = "desconto", nullable = false, precision = 0)
    private Double desconto;
    @ManyToOne(targetEntity = Race.class)
    private Race race;
}
