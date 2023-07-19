package com.covoiturage.paypal.commonApi.Event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentCreatedEvent {

    private  String paymentId;
    private  double price;
    private  String currency;
    private  String method;
    private  String intent;
    private  String description;
    private  String cancelUrl;
    private  String successUrl;
    private  String approvalUrl;


}
