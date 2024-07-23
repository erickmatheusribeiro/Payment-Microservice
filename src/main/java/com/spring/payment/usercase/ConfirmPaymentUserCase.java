package com.spring.payment.usercase;

import com.spring.payment.entities.Payment;
import com.spring.payment.interfaceadapters.gateways.PaymentGateway;
import com.spring.payment.util.enums.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfirmPaymentUserCase {

    @Autowired
    public PaymentUserCase paymentUserCase;

    @Autowired
    private PaymentGateway paymentGateway;

    @Scheduled(fixedRate = 30000)
    public void confirmPayment(){
        List<Payment> payments = paymentGateway.findByStatus(PaymentStatus.PENDING);

        payments.stream().forEach(payment -> {
            paymentUserCase.updateStatus(Long.valueOf(payment.getId()), PaymentStatus.COMPLETED);
        });

    }

}
