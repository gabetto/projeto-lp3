package com.JavaRunner.JavaRunner.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "runner_subscribes")
@EqualsAndHashCode(callSuper = true)
public class SubscribeRace extends DatabaseCommons {
    @Column(length = 36)
    private String raceId;
    @Column(length = 36)
    private String runnerId;
    private Double price;
    private Boolean doPayment;
}
