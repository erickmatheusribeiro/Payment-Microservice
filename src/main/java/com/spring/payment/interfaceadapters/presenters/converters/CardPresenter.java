package com.spring.payment.interfaceadapters.presenters.converters;

import com.spring.payment.entities.Card;
import com.spring.payment.interfaceadapters.presenters.dto.CardDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardPresenter {
    public CardDto mapToDto(Card card) {
        CardDto dto = new CardDto();

        dto.setUsers(card.getUsers());
        dto.setStatus(card.isStatus());
        dto.setName(card.getName());
        dto.setCardNumber(card.getCardNumber());
        dto.setExpirationDate(card.getExpirationDate());
        dto.setCvv(card.getCvv());

        return dto;
    }

    public Card mapToEntity(CardDto dto){
        Card entity = new Card();

        entity.setUsers(dto.getUsers());
        entity.setStatus(dto.isStatus());
        entity.setName(dto.getName());
        entity.setCardNumber(dto.getCardNumber());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setCvv(dto.getCvv());

        return entity;
    }
}
