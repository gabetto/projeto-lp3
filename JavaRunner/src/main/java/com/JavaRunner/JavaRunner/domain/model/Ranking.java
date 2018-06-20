package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "ranking")
public class Ranking extends DatabaseCommons {
    @Basic
    @Column(nullable = false, length = 64)
    private String rankingName;
    @Basic
    @Column(nullable = false, length = 64)
    private String organizer;
    @Column(length = 64)
    private String state;
    @Column(length = 64)
    private String region;
    @Column(length = 64)
    private String city;
    @Column(nullable = false)
    private Integer initialAge;
    @Column(nullable = false)
    private Integer finalAge;
    @JoinTable
    @ManyToMany
    private Collection<Race> races;
}
