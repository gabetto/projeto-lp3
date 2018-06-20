package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rules")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Rule extends DatabaseCommons {
    @Column(name = "descricao", nullable = false, length = 45)
    private String description;
}
