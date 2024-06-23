package com.spring.payment.interfaceadapters.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.payment.entities.Card;
import com.spring.payment.interfaceadapters.gateways.CardGateway;
import com.spring.payment.interfaceadapters.presenters.converters.CardPresenter;
import com.spring.payment.interfaceadapters.presenters.dto.CardDto;
import com.spring.payment.usercase.CardUserCase;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CardController {

    @Resource
    private CardUserCase business;

    @Resource
    private CardPresenter presenter;

    @Resource
    private CardGateway gateway;

    public CardDto insert(CardDto cardDto) throws JsonProcessingException {

        Card card = business.createCard(cardDto);
        gateway.insert(card);

        return presenter.mapToDto(card);

    }

    public CardDto findByCardNumber(String cardNumber) throws JsonProcessingException {
        Optional<Card> card = Optional.ofNullable(gateway.findByCardNumber(cardNumber));

        return presenter.mapToDto(card.orElse(null));
    }

    public CardDto inactiveCard(String cardNumber) throws JsonProcessingException {
        Card card = gateway.findByCardNumber(cardNumber);
        card.setStatus(false);
        gateway.update(card);

        return presenter.mapToDto(card);
    }
}
