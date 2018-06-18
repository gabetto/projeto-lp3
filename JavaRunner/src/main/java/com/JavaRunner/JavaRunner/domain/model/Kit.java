package com.JavaRunner.JavaRunner.domain.model;

import com.JavaRunner.JavaRunner.utils.contracts.ModelValidation;
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
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "tipo", nullable = false, length = 64)
    private String tipo;
    @Column(name = "nome", nullable = false, length = 64)
    private String nome;
    @Column(name = "descricao", nullable = true, length = 1024)
    private String descricao;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Race.class)
    private Race race;
    @OneToMany(mappedBy = "kit", targetEntity = Product.class)
    private Collection<Product> products;
    @Override
    public Kit validate() throws Exception {
        return null;
    }
}
