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

    @Column(name = "userid")
    private String users;

    @Column(name = "status")
    private boolean status;

    @Column(name = "name")
    private String name;

    @Column(name ="cardnumber")
    private String cardNumber;

    @Column(name = "validation")
    private String expirationDate;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "datetimecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeCreated;
}
