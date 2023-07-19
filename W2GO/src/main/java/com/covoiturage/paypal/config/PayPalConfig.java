package com.covoiturage.paypal.config;
import com.paypal.base.rest.APIContext;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class PayPalConfig {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Bean
    public PayPalHttpClient payPalHttpClient() {
        // Configuration du client PayPal
        PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, clientSecret);
        PayPalHttpClient httpClient = new PayPalHttpClient(environment);
        return httpClient;
    }

    @Bean
    public APIContext apiContext() {
        // Configuration de l'API PayPal
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

        return apiContext;
    }

}





