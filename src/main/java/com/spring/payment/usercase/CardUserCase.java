package com.spring.payment.usercase;

import com.spring.payment.entities.Card;
import com.spring.payment.interfaceadapters.presenters.dto.CardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class CardUserCase {
    @Autowired
    private Clock clock;

    public Card createCard(CardDto dto) {
        Card entity = new Card();

        entity.setUsers(dto.getUsers());
        entity.setStatus(dto.isStatus());
        entity.setName(dto.getName());
        entity.setCardNumber(dto.getCardNumber());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setCvv(dto.getCvv());

        entity.setDateTimeCreated(LocalDateTime.now(clock));

        return entity;
    }
}
