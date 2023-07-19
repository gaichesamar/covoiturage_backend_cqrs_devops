package com.covoiturage.paypal.commande.controllers;

import com.covoiturage.paypal.commande.service.ExecutePaymentCommandHandler;
import com.covoiturage.paypal.commonApi.Dt.CreatePaymentCommand;
import com.covoiturage.paypal.commonApi.Dt.ExecutePaymentCommand;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins ="http://localhost:4200/")
@RestController
public class PaymentController {
    @Autowired
    private APIContext apiContext;
    private final CommandGateway commandGateway;
    public static final String successUrl = "http://localhost:4200/pay/success";
    public static final String cancelUrl = "http://localhost:4200/pay/cancel";
    @Autowired
    private ExecutePaymentCommandHandler executePaymentCommandHandler;

    @Autowired
    public PaymentController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/createPayment", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public CompletableFuture<String> createPayment(@RequestBody CreatePaymentCommand request) {
        CreatePaymentCommand command = new CreatePaymentCommand(
                request.getPaymentId(),
                request.getPrice(),
                request.getCurrency(),
                request.getMethod(),
                request.getIntent(),
                request.getDescription(),
                cancelUrl,
                successUrl);

        return commandGateway.send(command)
                .thenApply(paymentId -> {
                    // Récupérer l'URL d'approbation en utilisant paymentId
                    String approvalUrl = getApprovalUrl((String) paymentId);
                    // Créer un objet JSON contenant l'URL d'approbation
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("approvalUrl", approvalUrl);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    // Retourner la réponse JSON
                    return jsonObject.toString();
                });
    }
    private String getApprovalUrl(String paymentId) {
        try {
            Payment payment = Payment.get(apiContext, paymentId);
            List<Links> links = payment.getLinks();
            for (Links link : links) {
                if ("approval_url".equals(link.getRel())) {
                    return link.getHref();
                }
            }
            throw new IllegalArgumentException("Approval URL not found");
        } catch (PayPalRESTException e) {
            throw new RuntimeException("Failed to retrieve approval URL from PayPal", e);
        }
    }
    @GetMapping(value = successUrl)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        ExecutePaymentCommand command = new ExecutePaymentCommand(paymentId, payerId);
        commandGateway.sendAndWait(command);

        // Rediriger vers la page de confirmation de paiement réussi
        return "redirect:/success";
    }

}




