package com.example.testApplication.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cards")
@Data
public class Card extends BaseEntity{

  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "cardNumber")
  private String cardNumber;

  @Column(name = "balance")
  private BigDecimal balance;

  @Column(name = "isActive")
  private Boolean isActive;

  @Column(name = "clientId")
  private String clientId;

}
