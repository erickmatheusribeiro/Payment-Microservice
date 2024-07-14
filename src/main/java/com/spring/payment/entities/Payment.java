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

    @Column(name = "userid")
    private String users;

    @Column(name = "orderid")
    private String orders;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "value", precision = 15, scale = 4)
    private BigDecimal value;

    @Column(name = "datetimecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeCreated;

    @Column(name = "datetimeend")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeEnd;

    @Column(name = "datetimecancel")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeCancel;

    @Column(name = "totalcardinstallment")
    private int totalCardInstallment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cardid")
    private Card card;
}
