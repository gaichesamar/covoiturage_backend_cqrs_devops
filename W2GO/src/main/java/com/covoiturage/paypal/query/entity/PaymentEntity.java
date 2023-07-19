package com.covoiturage.paypal.query.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    private String paymentId;

    private double price;

    private String currency;

    private String method;

    private String intent;

    private String description;

    private String cancelUrl;

    private String successUrl;


}