package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "ranking")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Ranking extends DatabaseCommons {
    @Column(nullable = false, length = 64)
    private String rankingName;
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
