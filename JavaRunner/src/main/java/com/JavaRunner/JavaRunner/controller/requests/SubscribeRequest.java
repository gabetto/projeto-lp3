package com.JavaRunner.JavaRunner.controller.requests;

import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.model.Runner;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SubscribeRequest {
    private String raceId;
    private Double price;
    private Boolean doPayment;
    private String runnerId;
    private Race race;
    private Runner runner;

    public SubscribeRequest() {
    }
}