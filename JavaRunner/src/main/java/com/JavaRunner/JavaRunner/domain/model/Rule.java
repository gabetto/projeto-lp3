package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Accessors(chain = true)
public class Rule {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "descricao", nullable = false, length = 45)
    private String description;
}
