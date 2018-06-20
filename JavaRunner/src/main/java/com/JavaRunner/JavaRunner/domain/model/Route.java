package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "routes")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Route extends DatabaseCommons  {
    @Column(nullable = false, length = 64)
    private String name;
    @Column(nullable = false, precision = 0)
    private Double length;
    @Column(nullable = false, length = 1024)
    private String description;
    @Column(nullable = false, length = 20)
    private String startCep;
    @Column(nullable = false, length = 20)
    private String finalCep;
    @Column(length = 1024)
    private String finalInfo;
    @Column(length = 1024)
    private String startInfo;
    @Column
    private Double points;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Race race;

    public Route validate() throws Exception {
        return null;
    }
}
