package com.example.testApplication.service;

import com.example.testApplication.dto.CardDto;
import com.example.testApplication.dto.TransferDto;
import com.example.testApplication.exception.CardIsNotActiveException;
import com.example.testApplication.exception.NoSuchCardException;
import com.example.testApplication.exception.SubZeroBalanceException;
import com.example.testApplication.mapper.CardMapper;
import com.example.testApplication.mapper.TransferMapper;
import com.example.testApplication.model.Card;
import com.example.testApplication.repository.CardRepository;
import com.example.testApplication.repository.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final TransferRepository transferRepository;
    private final CardMapper cardMapper;
    private final TransferMapper transferMapper;

    @Retryable
    @Transactional
    public CardDto createCard(CardDto dto) {
        Card card = new Card();

        String cardNumber = RandomStringUtils.randomNumeric(16);
        card.setCardNumber(cardNumber);
        card.setBalance(BigDecimal.ZERO);
        card.setIsActive(false);
        card.setClientId(dto.getClientId());

        cardRepository.save(card);

        return cardMapper.entityToDto(card);
    }

    public CardDto issueCard(String cardNumber) throws NoSuchCardException {
        Card card = cardRepository.findByCardNumber(cardNumber);
        if (card == null) throw new NoSuchCardException();
        card.setIsActive(true);
        cardRepository.save(card);

        return cardMapper.entityToDto(card);
    }

    public TransferDto transferMoneyToCard(TransferDto dto) throws CardIsNotActiveException, SubZeroBalanceException, NoSuchCardException {
        Card senderCard = cardRepository.findByCardNumber(dto.getSenderCardNum());
        Card recipientCard = cardRepository.findByCardNumber(dto.getRecipientCardNum());
        if (senderCard == null || recipientCard == null) throw new NoSuchCardException();
        if (!senderCard.getIsActive() || !recipientCard.getIsActive()) throw new CardIsNotActiveException();
        if (senderCard.getBalance().subtract(dto.getValue()).compareTo(BigDecimal.ZERO) < 0)
            throw new SubZeroBalanceException();

        senderCard.setBalance(senderCard.getBalance().subtract(dto.getValue()));
        recipientCard.setBalance(recipientCard.getBalance().add(dto.getValue()));

        cardRepository.save(senderCard);
        cardRepository.save(recipientCard);
        transferRepository.save(transferMapper.dtoToEntity(dto));

        return dto;
    }

    public CardDto accrueMoney(TransferDto dto) throws CardIsNotActiveException, NoSuchCardException {
        Card card = cardRepository.findByCardNumber(dto.getRecipientCardNum());
        if (card == null) throw new NoSuchCardException();
        if (!card.getIsActive()) throw new CardIsNotActiveException();
        card.setBalance(card.getBalance().add(dto.getValue()));

        cardRepository.save(card);

        return cardMapper.entityToDto(card);
    }

    public List<CardDto> getAllCards(String clientId) {
        return cardRepository.findByClientId(clientId)
                .stream()
                .map(cardMapper::entityToDto)
                .collect(Collectors.toList());
    }

}
