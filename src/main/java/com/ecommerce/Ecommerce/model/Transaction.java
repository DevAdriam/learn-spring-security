package com.ecommerce.Ecommerce.model;

import jakarta.persistence.*;


@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private  String id;

    @Column(name = "credit",columnDefinition ="FLOAT DEFAULT 0.0")
    private double credit;

    @Column(name = "debit", columnDefinition = "FLOAT DEFAULT 0.0")
    private double debit;

    @OneToOne(mappedBy = "transaction")
    private User user;
}
