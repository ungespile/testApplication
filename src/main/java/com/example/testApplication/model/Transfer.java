package com.example.testApplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "transfers")
@Data
public class Transfer extends BaseEntity {

    @Column(name = "senderCardNum")
    private String senderCardNum;

    @Column(name = "recipientCardNum")
    private String recipientCardNum;

    @Column(name = "value")
    private BigDecimal value;

}
