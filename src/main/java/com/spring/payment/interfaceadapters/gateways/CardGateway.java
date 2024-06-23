package com.spring.payment.interfaceadapters.gateways;

import com.spring.payment.entities.Card;
import com.spring.payment.frameworks.db.CardRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CardGateway {
    @Resource
    private CardRepository repository;

    public Card findByCardNumber(String cardNumber){
        return repository.findByCardNumber(cardNumber);
    }

    public Card insert(Card card){
        return repository.save(card);
    }

    public Card update(Card card){
        return repository.save(card);
    }
}
