package com.spring.payment.interfaceadapters.controllers;

import com.spring.payment.entities.Payment;
import com.spring.payment.interfaceadapters.gateways.PaymentGateway;
import com.spring.payment.interfaceadapters.presenters.converters.PaymentPresenter;
import com.spring.payment.interfaceadapters.presenters.dto.PaymentDto;
import com.spring.payment.usercase.PaymentUserCase;
import com.spring.payment.util.enums.PaymentStatus;
import com.spring.payment.util.exception.BusinessException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PaymentController {
    @Resource
    private PaymentUserCase business;

    @Resource
    private PaymentPresenter presenter;

    @Resource
    private PaymentGateway gateway;


    public PaymentDto insert(PaymentDto paymentdto) throws BusinessException {

        Payment payment = business.createPayment(paymentdto);
        gateway.insert(payment);

        return presenter.mapToDto(payment);

    }

    public PaymentDto updateStatus(Long paymentId, PaymentStatus status){
        Optional<Payment> dto = business.updateStatus(paymentId, status);

        return presenter.mapToDto(dto.orElse(null));
    }


}
