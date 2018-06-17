package com.jabarunner.JabaRunner.domain.model;

import com.jabarunner.JabaRunner.utils.contracts.ModelValidation;
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
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "nome", nullable = false, length = 64)
    private String name;
    @Basic
    @Column(name = "kilometragem", nullable = false, precision = 0)
    private Double maxLength;
    @Basic
    @Column(name = "descricao", nullable = false, length = 1024)
    private String description;
    @Basic
    @Column(name = "cep_largada", nullable = false, length = 9)
    private String startCep;
    @Basic
    @Column(name = "cep_chegada", nullable = false, length = 9)
    private String finalCep;
    @Basic
    @Column(name = "info_chegada", nullable = true, length = 1024)
    private String finalInfo;
    @Basic
    @Column(name = "info_largada", nullable = true, length = 1024)
    private String startInfo;
    @Basic
    @Column(name = "pontuado", nullable = true, precision = 0)
    private Double points;
    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    private Race race;

    @Override
    public Route validate() throws Exception {
        return null;
    }
}
