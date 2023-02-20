package com.example.testApplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDto {
    private String senderCardNum;
    private String recipientCardNum;
    @JsonProperty(required = true)
    private BigDecimal value;
}
