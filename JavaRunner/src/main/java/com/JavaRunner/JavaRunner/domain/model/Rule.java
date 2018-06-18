package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class Rule {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "descricao", nullable = false, length = 45)
    private String description;
}
