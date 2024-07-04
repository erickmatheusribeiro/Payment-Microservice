package com.spring.payment.usercase;

import com.spring.payment.entities.Card;
import com.spring.payment.entities.Payment;
import com.spring.payment.interfaceadapters.gateways.CardGateway;
import com.spring.payment.interfaceadapters.gateways.PaymentGateway;
import com.spring.payment.interfaceadapters.presenters.converters.CardPresenter;
import com.spring.payment.interfaceadapters.presenters.dto.PaymentDto;
import com.spring.payment.util.enums.PaymentStatus;
import com.spring.payment.util.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PaymentUserCase {
    @Autowired
    private Clock clock;

    @Autowired
    private CardGateway cardGateway;

    @Autowired
    private CardPresenter cardPresenter;

    @Autowired
    private PaymentGateway paymentGateway;

    public Payment createPayment(PaymentDto dto) throws BusinessException{
        Payment entity = new Payment();

        if(dto.getValue().compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException("PAYMENT_VALUE_INVALID");
        }
        if(dto.getTotalCardInstallment() <= 0){
            throw new BusinessException("PAYMENT_TOTAL_CARD_INSTALLMENT_INVALID");
        }

        Card cardEntity = new Card();

        entity.setStatus(PaymentStatus.PENDING);
        entity.setUsers(dto.getUser());
        entity.setOrders(dto.getOrder());
        entity.setDateTimeCreated(LocalDateTime.now(clock));
        entity.setTotalCardInstallment(dto.getTotalCardInstallment());
        entity.setValue(dto.getValue());


        if( cardGateway.findByCardNumber(dto.getCard().getCardNumber()) == null){

            cardEntity.setUsers(dto.getUser());
            cardEntity.setCardNumber(dto.getCard().getCardNumber());
            cardEntity.setName(dto.getCard().getName());
            cardEntity.setExpirationDate(dto.getCard().getExpirationDate());
            cardEntity.setCvv(dto.getCard().getCvv());
            cardEntity.setStatus(true);
            cardEntity.setDateTimeCreated(LocalDateTime.now(clock));

            entity.setCard(
                    cardGateway.insert(cardEntity)
            );
        } else {
            entity.setCard(cardGateway.findByCardNumber(dto.getCard().getCardNumber()));
        }

        return paymentGateway.insert(entity);
    }

    public Optional<Payment> updateStatus(Long paymentId, PaymentStatus status){
        Optional<Payment> payment = paymentGateway.findById(paymentId);
        payment.get().setStatus(status);

        if(PaymentStatus.COMPLETED.equals(status)){
            payment.get().setDateTimeEnd(LocalDateTime.now(clock));
        } else if(PaymentStatus.CANCELLED.equals(status)){
            payment.get().setDateTimeCancel(LocalDateTime.now(clock));
        } else if(PaymentStatus.PENDING.equals(status)){
            payment.get().setDateTimeEnd(null);
            payment.get().setDateTimeCancel(null);
        }

        return paymentGateway.update(payment.orElse(null));
    }
}
