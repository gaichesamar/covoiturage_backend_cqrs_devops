package com.covoiturage.paypal.commande.aggregates;

import com.covoiturage.paypal.commonApi.Dt.ExecutePaymentCommand;
import com.covoiturage.paypal.commonApi.Event.PaymentExecutedEvent;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PaymentExecutionAggregate {

    @AggregateIdentifier
    private String paymentId;

    private boolean paymentExecuted;
    @Autowired
    private  APIContext apiContext;

    @CommandHandler
    public PaymentExecutionAggregate(ExecutePaymentCommand command,APIContext apiContext) throws PayPalRESTException {
        this.apiContext = apiContext;
        Objects.requireNonNull(command.getPaymentId(), "Payment ID cannot be null");
        Objects.requireNonNull(command.getPayerId(), "Payer ID cannot be null");

        Payment payment = new Payment();
        payment.setId(command.getPaymentId());

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(command.getPayerId());

        payment.execute(apiContext, paymentExecution);

        PaymentExecutedEvent event = new PaymentExecutedEvent(command.getPaymentId(), command.getPayerId());
        apply(event);
    }

    @EventSourcingHandler
    public void on(PaymentExecutedEvent event) {
        this.paymentId = event.getPaymentId();
        this.paymentExecuted = true;
    }
    public void setApiContext(APIContext apiContext) {
        this.apiContext = apiContext;
    }
}