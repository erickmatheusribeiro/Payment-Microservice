package com.spring.payment.entities;

import com.spring.payment.util.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "payment")
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String users;

    @Column(nullable = false)
    private String orders;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;

    @Column(precision = 15, scale = 4)
    private BigDecimal value;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeCreated;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeEnd;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeCancel;

    @Column(nullable = false, length = 2)
    private int totalCardInstallment;

    @OneToOne(cascade = CascadeType.ALL)
    private Card card;
}
