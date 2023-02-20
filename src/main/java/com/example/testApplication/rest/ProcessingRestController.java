package com.example.testApplication.rest;


import com.example.testApplication.dto.CardDto;
import com.example.testApplication.dto.TransferDto;
import com.example.testApplication.exception.BadRequestException;
import com.example.testApplication.exception.CardIsNotActiveException;
import com.example.testApplication.exception.NoSuchCardException;
import com.example.testApplication.exception.SubZeroBalanceException;
import com.example.testApplication.model.Card;
import com.example.testApplication.repository.CardRepository;
import com.example.testApplication.service.CardService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/card/")
@Slf4j
public class ProcessingRestController {

    private final CardService cardService;

    public ProcessingRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("create")
    public CardDto createCard(@RequestBody CardDto dto) throws BadRequestException {
        if(dto.getClientId() == null) throw new BadRequestException("client id is null");
        return cardService.createCard(dto);
    }

    @PostMapping("issue")
    @Transactional
    public CardDto issueCard(@RequestBody CardDto dto) throws NoSuchCardException, BadRequestException {
        if(dto.getCardNumber() == null) throw new BadRequestException("cardNumber id is null");
        return cardService.issueCard(dto.getCardNumber());
    }

    @PostMapping("transfer")
    @Transactional
    public TransferDto transferMoneyToCard(@RequestBody TransferDto dto) throws CardIsNotActiveException, SubZeroBalanceException, NoSuchCardException, BadRequestException {
        if(dto.getValue() == null) throw new BadRequestException("value is null");
        return cardService.transferMoneyToCard(dto);
    }

    @PostMapping("accrue")
    @Transactional
    public CardDto accrueMoney(@RequestBody TransferDto dto) throws CardIsNotActiveException, NoSuchCardException, BadRequestException {
        if(dto.getValue() == null) throw new BadRequestException("value is null");
        return cardService.accrueMoney(dto);
    }

    @GetMapping ("getAllCards")
    @Transactional
    public List<CardDto> getAllCards(@RequestParam(value = "clientId") String clientId){
        return cardService.getAllCards(clientId);
    }
}
