package com.example.testApplication.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDto {

    private String cardNumber;
    private BigDecimal balance;
    private Boolean isActive;
    private String clientId;

}
