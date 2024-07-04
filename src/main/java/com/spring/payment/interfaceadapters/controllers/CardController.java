package com.spring.payment.interfaceadapters.controllers;

import com.spring.payment.interfaceadapters.presenters.dto.CardDto;
import com.spring.payment.usercase.CardUserCase;
import com.spring.payment.util.exception.BusinessException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CardController {

    @Resource
    private CardUserCase business;



    public CardDto insert(CardDto cardDto) throws BusinessException {

       return business.createCard(cardDto);

    }

    public Optional<CardDto> findByCardNumber(String cardNumber) throws BusinessException {
        return business.findByCardNumber(cardNumber);
    }

    public CardDto inactiveCard(String cardNumber) throws BusinessException {
        return business.inactiveCard(cardNumber);
    }
}
