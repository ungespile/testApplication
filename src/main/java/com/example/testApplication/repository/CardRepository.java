package com.example.testApplication.repository;

import com.example.testApplication.model.Card;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Card findByCardNumber(String cardNumber);

    List<Card> findByClientId(String clientId);

}
