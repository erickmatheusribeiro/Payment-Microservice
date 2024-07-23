package com.spring.payment.interfaceadapters.gateways;

import com.spring.payment.entities.Payment;
import com.spring.payment.frameworks.db.PaymentRepository;
import com.spring.payment.util.enums.PaymentStatus;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentGateway {
    @Resource
    private PaymentRepository repository;

    public Optional<Payment> findByOrderId(String orderId){
        return Optional.ofNullable(repository.findByOrders(orderId));
    }

    public Optional<Payment> findById(Long paymentId){
        return repository.findById(paymentId);
    }

    public Payment insert(Payment payment){
        return repository.save(payment);
    }

    public Optional<Payment> update(Payment payment){
        return Optional.of(repository.save(payment));
    }

    public List<Payment> findByStatus(PaymentStatus status){
        return repository.findByStatus(status);
    }
}
