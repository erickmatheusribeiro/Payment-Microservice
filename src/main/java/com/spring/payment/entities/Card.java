package com.spring.payment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity(name = "card")
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String users;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String expirationDate;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeCreated;
}
