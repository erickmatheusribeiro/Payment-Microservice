package com.spring.payment.frameworks.db;

import com.spring.payment.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String cardNumber);
}
