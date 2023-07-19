package com.covoiturage.paypal.commande.aggregates;


import com.covoiturage.paypal.commonApi.Dt.CreatePaymentCommand;
import com.covoiturage.paypal.commonApi.Event.PaymentCreatedEvent;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PaymentAggregate {
    @Autowired
    private APIContext apiContext;


    @AggregateIdentifier
    private String paymentId;
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String cancelUrl;
    private String successUrl;

    @CommandHandler
    public PaymentAggregate(CreatePaymentCommand command, APIContext apiContext) throws PayPalRESTException {
        this.apiContext = apiContext;
        Objects.requireNonNull(command.getMethod(), "Payment method cannot be null");
        Amount amount = new Amount();
        amount.setCurrency(command.getCurrency());
        BigDecimal decimalAmount = BigDecimal.valueOf(command.getPrice()).setScale(2, RoundingMode.HALF_UP);
        amount.setTotal(decimalAmount.toString());

        Transaction transaction = new Transaction();
        transaction.setDescription(command.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(command.getMethod().toString());

        Payment payment = new Payment();
        // Assurez-vous que le champ "intent" est défini
        payment.setIntent(command.getIntent().toString());
        // Assurez-vous que le champ "payer" est défini
        payment.setPayer(payer);

        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();

        redirectUrls.setCancelUrl(command.getCancelUrl());
        redirectUrls.setReturnUrl(command.getSuccessUrl());
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(apiContext);

        String approvalUrl = createdPayment.getLinks().stream()
                .filter(link -> "approval_url".equals(link.getRel()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .getHref();

        // Création de l'événement correspondant et envoi de l'événement
        PaymentCreatedEvent event = new PaymentCreatedEvent(
                createdPayment.getId(),
                command.getPrice(),
                command.getCurrency(),
                command.getMethod(),
                command.getIntent(),
                command.getDescription(),
                command.getCancelUrl(),
                command.getSuccessUrl(),
                approvalUrl);

        apply(event);
    }
    @EventSourcingHandler
    public void on(PaymentCreatedEvent event) {
        // Mise à jour de l'état de l'agrégat
        this.paymentId = event.getPaymentId();
        this.price = event.getPrice();
        this.currency = event.getCurrency();
        this.method = event.getMethod();
        this.intent = event.getIntent();
        this.description = event.getDescription();
        this.cancelUrl = event.getCancelUrl();
        this.successUrl = event.getSuccessUrl();
    }

}