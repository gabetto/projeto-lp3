package com.jabarunner.JabaRunner.domain.model;

import com.jabarunner.JabaRunner.domain.utils.contracts.ModelValidation;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "ranking")
public class Ranking implements Serializable, ModelValidation<Ranking> {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;
    @Basic
    @Column(name = "nome_ranking", nullable = false, length = 64)
    private String nomeRanking;
    @Basic
    @Column(name = "organizer", nullable = false, length = 64)
    private String organizador;
    @Basic
    @Column(name = "estado", nullable = true, length = 64)
    private String estado;
    @Basic
    @Column(name = "regiao", nullable = true, length = 64)
    private String regiao;
    @Basic
    @Column(name = "cidade", nullable = true, length = 64)
    private String cidade;
    @Basic
    @Column(name = "idade_inicial", nullable = false)
    private Integer idadeInicial;
    @Basic
    @Column(name = "idade_final", nullable = false)
    private Integer idadeFinal;

    @ManyToMany
    @JoinTable(name = "corrida_has_ranking",
            joinColumns = @JoinColumn(name = "rankings_id"),
            inverseJoinColumns = @JoinColumn(name = "corridas_id"))
    private Collection<Race> Race;

    @Override
    public Ranking validate() throws Exception {
        return null;
    }
}
