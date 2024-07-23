package com.spring.payment.frameworks.db;

import com.spring.payment.entities.Payment;
import com.spring.payment.util.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrders(String orderId);

    Optional<Payment> findById(Long paymentId);

    List<Payment> findByStatus(PaymentStatus status);
}
