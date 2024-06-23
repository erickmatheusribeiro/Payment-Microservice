package com.spring.payment.frameworks.db;

import com.spring.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrders(String orderId);

    Optional<Payment> findById(Long paymentId);
}
