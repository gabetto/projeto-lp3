package com.jabarunner.JabaRunner.domain.model;

import com.jabarunner.JabaRunner.domain.utils.contracts.ModelValidation;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "produto")
public class Product implements Serializable, ModelValidation<Product> {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nome", nullable = false, length = 64)
    private String nome;
    @Basic
    @Column(name = "descricao", nullable = false, length = 1024)
    private String descricao;
    @Basic
    @Column(name = "preco", nullable = false, precision = 0)
    private Double preco;
    @Basic
    @Column(name = "estoque", nullable = false)
    private Integer estoque;
    @Basic
    @Column(name = "info_adicional", nullable = true, length = 1024)
    private String infoAdicional;
    @ManyToOne
    private Kit kit;

    @Override
    public Product validate() throws Exception {
        return null;
    }
}
