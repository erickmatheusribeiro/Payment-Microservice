package com.spring.payment.interfaceadapters.presenters.converters;

import com.spring.payment.entities.Payment;
import com.spring.payment.interfaceadapters.presenters.dto.PaymentDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentPresenter {
    public PaymentDto mapToDto(Payment payment) {
        PaymentDto dto = new PaymentDto();

        return dto;
    }
}
