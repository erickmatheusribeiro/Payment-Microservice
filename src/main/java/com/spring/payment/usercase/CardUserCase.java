package com.spring.payment.usercase;

import com.spring.payment.entities.Card;
import com.spring.payment.interfaceadapters.gateways.CardGateway;
import com.spring.payment.interfaceadapters.presenters.converters.CardPresenter;
import com.spring.payment.interfaceadapters.presenters.dto.CardDto;
import com.spring.payment.util.exception.BusinessException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static jakarta.xml.bind.DatatypeConverter.parseDate;

@Component
public class CardUserCase {
    @Autowired
    private Clock clock;

    @Resource
    private CardGateway gateway;

    @Resource
    private CardPresenter presenter;

    public CardDto createCard(CardDto dto) throws BusinessException {
        Card card = gateway.findByCardNumber(dto.getCardNumber());

        if(card != null
                && card.getCardNumber().equals(dto.getCardNumber())
                && card.getUsers().equals(dto.getUsers())){
            throw new BusinessException("CARD_DUPLICATE");
        }

        Card entity = new Card();

        entity.setUsers(dto.getUsers());
        entity.setStatus(dto.isStatus());
        entity.setName(dto.getName());
        entity.setCardNumber(dto.getCardNumber());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setCvv(dto.getCvv());

        entity.setDateTimeCreated(LocalDateTime.now(clock));

        gateway.insert(entity);

        return presenter.mapToDto(entity);
    }

    public Optional<CardDto> findByCardNumber(String cardNumber) throws BusinessException {
        Optional<Card> card = Optional.ofNullable(gateway.findByCardNumber(cardNumber));

        if(card.isEmpty()){
            throw new BusinessException("CARD_NUMBER_NOT_FOUND");
        }

        return card.map(presenter::mapToDto);
    }

    public CardDto inactiveCard(String cardNumber) throws BusinessException {
        Card card = gateway.findByCardNumber(cardNumber);

        if(card == null){
            throw new BusinessException("CARD_NUMBER_NOT_FOUND");
        }

        card.setStatus(false);
        gateway.update(card);

        return presenter.mapToDto(card);
    }
}
