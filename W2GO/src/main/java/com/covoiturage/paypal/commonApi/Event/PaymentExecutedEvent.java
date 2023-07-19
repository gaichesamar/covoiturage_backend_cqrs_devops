package com.covoiturage.paypal.commonApi.Event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentExecutedEvent {
    private  String paymentId;
    private  String payerId;
}
