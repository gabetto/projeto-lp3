package com.jabarunner.JabaRunner.domain.model;

import com.jabarunner.JabaRunner.domain.utils.contracts.ModelValidation;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@Accessors(chain = true)
public class Kit implements Serializable, ModelValidation<Kit> {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "tipo", nullable = false, length = 64)
    private String tipo;

    @Basic
    @Column(name = "nome", nullable = false, length = 64)
    private String nome;

    @Basic
    @Column(name = "descricao", nullable = true, length = 1024)
    private String descricao;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    private Race race;

    @OneToMany(mappedBy = "kit")
    private Collection<Product> products;

    @Override
    public Kit validate() throws Exception {
        return null;
    }
}
