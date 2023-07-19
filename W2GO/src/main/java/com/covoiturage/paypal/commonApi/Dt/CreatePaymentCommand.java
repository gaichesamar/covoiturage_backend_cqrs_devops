package com.covoiturage.paypal.commonApi.Dt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CreatePaymentCommand {

    @JsonProperty("paymentId")
    private String paymentId;
    @JsonProperty("price")
    private double price;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("method")
    private String method;
    @JsonProperty("intent")
    private String intent;
    @JsonProperty("description")
    private String description;
    @JsonProperty("cancelUrl")
    private String cancelUrl;
    @JsonProperty("successUrl")
    private String successUrl;

    public String getMethod() {
        // Vérifiez que la valeur de retour n'est pas nulle et retournez-la
        return method != null ? method : "PAYPAL";
    }
    public String getPaymentId() {
        return paymentId;
    }
}