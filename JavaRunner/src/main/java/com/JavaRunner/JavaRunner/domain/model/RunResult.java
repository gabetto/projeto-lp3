package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "run_result")
@EqualsAndHashCode(callSuper = true)
public class RunResult extends DatabaseCommons {
    @ManyToOne
    private Race race;
    @ManyToOne
    private Runner runner;
    private String runnerTime;
    private Double points;
}



