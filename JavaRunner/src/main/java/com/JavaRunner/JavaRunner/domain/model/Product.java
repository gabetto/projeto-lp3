package com.JavaRunner.JavaRunner.domain.model;

import com.JavaRunner.JavaRunner.utils.contracts.ModelValidation;
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
    @GeneratedValue
    private Integer id;
    @Column(name = "nome", nullable = false, length = 64)
    private String nome;
    @Column(name = "descricao", nullable = false, length = 1024)
    private String descricao;
    @Column(name = "preco", nullable = false, precision = 0)
    private Double preco;
    @Column(name = "estoque", nullable = false)
    private Integer estoque;
    @Column(name = "info_adicional", nullable = true, length = 1024)
    private String infoAdicional;
    @ManyToOne
    @JoinColumn(name = "kit_id")
    private Kit kit;
    @Override
    public Product validate() throws Exception {
        return null;
    }
}
