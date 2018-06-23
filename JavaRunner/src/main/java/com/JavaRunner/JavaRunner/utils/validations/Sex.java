package com.JavaRunner.JavaRunner.utils.validations;

import lombok.Getter;

@Getter
public enum Sex {
    MASCULINO("MASCULINO", "Masculino"),
    FEMININO("FEMININO", "Masculino");

    private String value;
    private String describe;

    Sex(String value, String describe) {
        this.value = value;
        this.describe = describe;
    }
}
