package com.spring.payment.usercase;

import com.spring.payment.entities.Payment;
import com.spring.payment.interfaceadapters.gateways.CardGateway;
import com.spring.payment.interfaceadapters.gateways.PaymentGateway;
import com.spring.payment.interfaceadapters.presenters.converters.CardPresenter;
import com.spring.payment.interfaceadapters.presenters.dto.CardDto;
import com.spring.payment.interfaceadapters.presenters.dto.PaymentDto;
import com.spring.payment.util.enums.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Payment createPayment(PaymentDto dto){
        Payment entity = new Payment();
        CardDto cardDto = new CardDto();

        entity.setStatus(PaymentStatus.PENDING);
        entity.setUsers(dto.getUser());
        entity.setOrders(dto.getOrder());
        entity.setDateTimeCreated(LocalDateTime.now(clock));

        cardDto = cardPresenter.mapToDto(cardGateway.findByCardNumber(dto.getCard().getCardNumber()));

        if( cardDto == null){

            cardDto.setUsers(dto.getUser());
            cardDto.setStatus(true);

            entity.setCard(
                    cardGateway.insert(
                            cardPresenter.mapToEntity(cardDto)
                    )
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

        return Optional.ofNullable(paymentGateway.insert(payment.orElse(null)));
    }
}
