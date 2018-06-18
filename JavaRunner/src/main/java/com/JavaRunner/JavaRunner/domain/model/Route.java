package com.JavaRunner.JavaRunner.domain.model;

import com.JavaRunner.JavaRunner.utils.contracts.ModelValidation;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "percurso")
public class Route implements Serializable, ModelValidation<Route> {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nome", nullable = false, length = 64)
    private String name;
    @Column(name = "kilometragem", nullable = false, precision = 0)
    private Double maxLength;
    @Column(name = "descricao", nullable = false, length = 1024)
    private String description;
    @Column(name = "cep_largada", nullable = false, length = 20)
    private String startCep;
    @Column(name = "cep_chegada", nullable = false, length = 20)
    private String finalCep;
    @Column(name = "info_chegada", nullable = true, length = 1024)
    private String finalInfo;
    @Column(name = "info_largada", nullable = true, length = 1024)
    private String startInfo;
    @Column(name = "pontuado", nullable = true, precision = 0)
    private Double points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corrida_id")
    private Race race;
    @Override
    public Route validate() throws Exception {
        return null;
    }
}
