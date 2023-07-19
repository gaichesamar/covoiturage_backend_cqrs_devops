package com.covoiturage.paypal.commonApi.Dt;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
@AllArgsConstructor
public class ExecutePaymentCommand {

    @TargetAggregateIdentifier
    private String paymentId;
    private String payerId;
}