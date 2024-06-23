package com.spring.payment.interfaceadapters.controllers;

import com.spring.payment.entities.Payment;
import com.spring.payment.interfaceadapters.presenters.converters.PaymentPresenter;
import com.spring.payment.interfaceadapters.presenters.dto.PaymentDto;
import com.spring.payment.usercase.PaymentUserCase;
import com.spring.payment.util.enums.PaymentStatus;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PaymentController {
    @Resource
    private PaymentUserCase business;

    @Resource
    private PaymentPresenter presenter;


    public PaymentDto insert(PaymentDto paymentdto){
        Payment dto = business.createPayment(paymentdto);

        return presenter.mapToDto(dto);

    }

    public PaymentDto updateStatus(Long paymentId, PaymentStatus status){
        Optional<Payment> dto = business.updateStatus(paymentId, status);

        return presenter.mapToDto(dto.orElse(null));
    }


}
