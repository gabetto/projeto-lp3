package com.JavaRunner.JavaRunner.controller.requests;

import com.JavaRunner.JavaRunner.domain.model.Kit;
import com.JavaRunner.JavaRunner.domain.model.Route;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaymentRequest {
    private Route route;
    private Kit kit;
    private String cardName;
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
    private String cep;
    private String rua;
    private String number;
    private String complement;
    private String state;
    private String city;
}
